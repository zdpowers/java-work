/**
 * Abstract class acting as a helper handler for the payment processing chain of responsibility
 * @author Zack Powers
 */

abstract class Account {

    protected Account successor;
    
    /**
     * Sets the next account in the Chain of Responsibility 
     * @param successor the next account to check if current account funds are insufficient
     */
    public void setSuccessor(Account successor) {
        this.successor = successor;
    }
    
    /**
     * Chain of Responsibility (CoR) method: Deducts the amount from the account, if funds insufficient calls on successor, if no successor throws exception
     * @param amount the amount to be charged to the account
     * @throws InsufficientFundsException exception thrown if the amount to be charged exceeds what the account can handle and there is no successor
     */
    abstract void deduct (double amount) throws InsufficientFundsException;
    
}
