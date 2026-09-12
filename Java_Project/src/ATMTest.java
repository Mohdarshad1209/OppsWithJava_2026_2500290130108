public class ATMTest {

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
            throw new InsufficientBalanceException("Insufficient balance.");
        }
        else {
            System.out.println("Withdrawal successful. New balance: "+ (balance - amount));
        }
    }
}
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}