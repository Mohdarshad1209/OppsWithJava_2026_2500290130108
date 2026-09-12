class InsufficientBalanceException extends Exception {
    InsufficientBalanceException(String message) {
        super(message);
    }
}
public class UseCse4 {

    public static void main(String[] args) {

        double balance = 5000.0;
        double withdrawAmount = 7500.0;

        try {
            withdraw(balance, withdrawAmount);
        }
        catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println("Transaction attempt completed.");
        }
    }

    static void withdraw(double balance, double amount)
            throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException(
                "Insufficient balance. Withdrawal cannot be completed."
            );
        }
        System.out.println(
            "Withdrawal successful. New balance: " + (balance - amount)
        );
    }
}