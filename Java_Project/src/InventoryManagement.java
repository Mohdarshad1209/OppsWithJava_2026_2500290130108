
package com.kiet.retail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;

public class InventoryManagement {

    static final String URL = "jdbc:mysql://localhost:3306/retail_db";
    static final String USER = "root";
    static final String PASSWORD = "2005";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try (Connection con = DriverManager.getConnection(
                URL, USER, PASSWORD)) {

            System.out.println("Database connected successfully!");

            int choice;

            do {
                System.out.println("\n===== INVENTORY MANAGEMENT =====");
                System.out.println("1. Insert 3 Products");
                System.out.println("2. Search Category (Quantity < 20)");
                System.out.println("3. Add Stock");
                System.out.println("4. Search and Update Price");
                System.out.println("5. Inventory Value and Delete");
                System.out.println("6. Exit");
                System.out.print("Enter choice: ");

                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 -> insertProducts(con);
                    case 2 -> retrieveProducts(con);
                    case 3 -> updateStock(con);
                    case 4 -> updatePrice(con);
                    case 5 -> inventoryValueAndDelete(con);
                    case 6 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice!");
                }

            } while (choice != 6);

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    static void insertProducts(Connection con) throws SQLException {
        String sql = "INSERT INTO Product VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            for (int i = 1; i <= 3; i++) {
                System.out.println("Product " + i);

                System.out.print("ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.print("Category: ");
                String category = sc.nextLine();

                System.out.print("Quantity: ");
                int quantity = sc.nextInt();

                System.out.print("Price: ");
                double price = sc.nextDouble();
                sc.nextLine();

                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setString(3, category);
                ps.setInt(4, quantity);
                ps.setDouble(5, price);

                try {
                    ps.executeUpdate();
                    System.out.println("Product inserted successfully!");
                } catch (SQLIntegrityConstraintViolationException e) {
                    System.out.println("Duplicate product ID!");
                }
            }
        }
    }

    static void retrieveProducts(Connection con) throws SQLException {
        System.out.print("Enter category: ");
        String category = sc.nextLine();

        String sql = "SELECT * FROM Product WHERE category = ? " + "AND quantity < 20 ORDER BY price DESC";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, category);

            try (ResultSet rs = ps.executeQuery()) {
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    displayProduct(rs);
                }
                if (!found) System.out.println("No products found.");
            }
        }
    }

    static void updateStock(Connection con) throws SQLException {
        System.out.print("Product ID: ");
        int id = sc.nextInt();

        System.out.print("Quantity to add: ");
        int qty = sc.nextInt();
        sc.nextLine();

        if (qty <= 0) {
            System.out.println("Quantity must be positive.");
            return;
        }

        String sql = "UPDATE Product SET quantity = quantity + ? " + "WHERE product_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, qty);
            ps.setInt(2, id);

            if (ps.executeUpdate() > 0)
                System.out.println("Stock updated successfully!");
            else
                System.out.println("Product does not exist.");
        }
    }

    static void updatePrice(Connection con) throws SQLException {
        System.out.print("Product ID: ");
        int id = sc.nextInt();

        System.out.print("New price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        if (price <= 0) {
            System.out.println("Price must be greater than zero.");
            return;
        }

        String sql = "SELECT * FROM Product WHERE product_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    System.out.println("Product does not exist.");
                    return;
                }
            }
        }

        try (PreparedStatement ps = con.prepareStatement(
                "UPDATE Product SET price = ? WHERE product_id = ?")) {
            ps.setDouble(1, price);
            ps.setInt(2, id);
            ps.executeUpdate();
        }

        System.out.println("Price updated successfully!");
        showProduct(con, id);
    }

    static void inventoryValueAndDelete(Connection con) throws SQLException {

        String sql = "SELECT SUM(quantity * price) AS total_value " + "FROM Product";

        try (Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)) {
            rs.next();
            System.out.printf("Total Inventory Value: %.2f%n",
                            rs.getDouble("total_value"));
        }

        System.out.print("Product ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        String delete = "DELETE FROM Product " +
                        "WHERE product_id = ? AND quantity = 0";

        try (PreparedStatement ps = con.prepareStatement(delete)) {
            ps.setInt(1, id);

            if (ps.executeUpdate() > 0) {
                System.out.println("Product deleted successfully!");
            } else {
                showDeleteFailure(con, id);
            }
        }
    }

    static void showDeleteFailure(Connection con, int id)
            throws SQLException {

        try (PreparedStatement ps = con.prepareStatement(
                "SELECT quantity FROM Product WHERE product_id = ?")) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next())
                    System.out.println("Product does not exist.");
                else
                    System.out.println(
                        "Cannot delete: quantity must be zero.");
            }
        }
    }

    static void showProduct(Connection con, int id) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM Product WHERE product_id = ?")) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) displayProduct(rs);
            }
        }
    }

    static void displayProduct(ResultSet rs) throws SQLException {
        System.out.println("-----------------------------");
        System.out.println("ID       : " + rs.getInt("product_id"));
        System.out.println("Name     : " + rs.getString("product_name"));
        System.out.println("Category : " + rs.getString("category"));
        System.out.println("Quantity : " + rs.getInt("quantity"));
        System.out.println("Price    : " + rs.getDouble("price"));
        System.out.println("-----------------------------");
    }
}
