/**
 * Main method driving the demonstration of the banking application
 * @author Zack Powers
 */

public class Main {
    
    public static void main(String[] args) {

        Customer customer = new Customer(200);

        customer.addAccount(new BankAccount("Savings", 50));
        
        customer.addAccount(new CreditAccount(50));
        customer.addAccount(new CreditAccount(50));
        customer.addAccount(new CreditAccount(50));
        
        customer.pay(150);
        customer.pay(220);
        customer.pay(5000);

    }
}
