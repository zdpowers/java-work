/**
 * Class representing a standard bank account with a balance of how much is in the account
 * @author Zack Powers
 */

public class BankAccount extends Account {
    
    private String name;
    private double balance;
    
    /**
     * Constructor
     * @param accountName the name of the account
     * @param startingBalance beginning balance to start the account with
     */
    public BankAccount(String accountName, double startingBalance) {
        name = accountName;
        balance = startingBalance;
    }
    
    @Override
    public void deduct(double amount) throws InsufficientFundsException {
        if(amount <= balance) {
            balance -= amount;
        } else if (successor != null) {
            successor.deduct(amount - balance);
            balance = 0;
        } else {
            throw new InsufficientFundsException("You have insufficient funds in your accounts.");
        }
    }
    
    /**
     * Adds the amount to the bank account balance
     * @param amount amount to be added to the balance
     */
    public void makeDeposit(double amount) {
        balance += amount;
    }

    @Override
    public String toString() {
        return name + " Bank Account";
    }
}
