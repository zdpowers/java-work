import java.util.LinkedList;

/**
 * A class representing a customer that can have multiple accounts of each type (BankAccount and CreditAccount)
 * @author Zack Powers
 */

public class Customer implements PayingParty {

    private BankAccount checking;
    private LinkedList<Account> accounts = new LinkedList<Account>();

    /**
     * The constructor creates an initial checking account for the customer with an initial balance.
     * @param startingbalance the intial balance for the checking account.
     */
    public Customer(double startingbalance) {
        checking = new BankAccount("Checking", startingbalance);
        accounts.addFirst(checking);
    }

    @Override
    public void pay (double amount) {
        //CheckProcessor.processCheck(accounts.getFirst(), amount);
        CheckProcessor.processCheck(checking, amount);
    }
    
    /**
     * adds the passed account as one of the customer’s accounts and puts it at the end of the customer’s CoR for payment
     * @param acc the new account to be added
     */
    public void addAccount(Account acc) {
        accounts.getLast().setSuccessor(acc);
        accounts.add(acc);
    }

    @Override
    public String toString() {
        return "Customer";
    }
}