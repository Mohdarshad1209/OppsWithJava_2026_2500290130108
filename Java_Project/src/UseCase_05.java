import java.util.*;
public class UseCase_05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int availableSeats = 10;
        try {
            System.out.print("Enter passenger age: ");
            int age = sc.nextInt();
            if (age <= 0) {
                throw new IllegalArgumentException("Invalid age. Age must be greater than 0.");
            }
            System.out.print("Enter number of seats: ");
            int seats = sc.nextInt();
            if (seats <= 0) {
                throw new IllegalArgumentException(
                    "Invalid number of seats. Seats must be greater than 0."
                );
            }
            if (seats > availableSeats) {
                throw new Exception("Insufficient seats available.");
            }
            System.out.println("Ticket booked successfully!");
            System.out.println("Age: " + age);
            System.out.println("Seats booked: " + seats);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
