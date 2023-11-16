/**
 * Interface for classes that need to make payments.
 * @author Zack Powers
 */

public interface PayingParty {
    /**
     * Initiates payment by the paying party for the given amount.
     * @param amount the amount to be paid
     */
    public void pay(double amount);
}
