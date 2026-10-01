import java.util.*;

class Product {
    int productId;
    String name;
    int price;
    Product(int id, String n, int p) {
        productId = id;
        name = n;
        price = p;
    }
    @Override
    public String toString() {
        return productId + " " + name + " " + price;
    }
}
class CustomComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        if (p1.price != p2.price) {
            return p2.price - p1.price;
        }
        return p1.name.compareTo(p2.name);
    }
}
public class Sorting2{
    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(101, "Laptop", 60000));
        products.add(new Product(102, "Mobile", 60000));
        products.add(new Product(103, "Tablet", 30000));
        products.add(new Product(104, "Mouse", 1000));
        products.sort(new CustomComparator());
        for (Product p : products) {
            System.out.println(p);
        }
    }
}