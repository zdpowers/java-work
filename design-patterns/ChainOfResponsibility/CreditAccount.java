/**
 * A class for credit accounts that have a credit limit and balance.
 * @author Zack Powers
 */

public class CreditAccount extends Account {

    private double creditLimit;
    private double balance;
    
    /**
     * Consttructor that initiates a credit account with a given gredit limit
     * @param limit the credit limit for the account
     */
    public CreditAccount(double limit) {
        creditLimit = limit;
    }

    @Override
    public void deduct(double amount) throws InsufficientFundsException {
        if(amount <= (creditLimit - balance)) {
            charge(amount);
        } else if (successor != null ) {
            successor.deduct(amount - (creditLimit - balance));
            balance = creditLimit;
        } else {
            throw new InsufficientFundsException("You have insufficient funds in your accounts.");
        }
    }
    
    /**
     * Charges the credit account the given amount, increasing the balance on the account. Adds to balance on this card only no CoR.
     * @param amount the amount to charge the account, this amount is added to the balance.
     */
    private void charge(double amount) throws InsufficientFundsException {
        if(amount <= (creditLimit - balance)) {
            balance += amount;
        } else {
            throw new InsufficientFundsException("You have insufficient funds in your accounts.");
        }
    }

    @Override
    public String toString() {
        return "Credit Account with " + creditLimit + " limit.";
    }
    
}
