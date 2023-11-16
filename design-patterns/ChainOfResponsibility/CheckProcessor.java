/**
 * Class containing static method used to process payments
 * @author Zack Powers
 */

public class CheckProcessor {
    
    public CheckProcessor() {}
    
    /**
     * The given account is charged the given amount and an exception is handled if there are not enough funds.
     * @param account the BankAccount account to be charged.
     * @param amount the amount to charge the account.
     * @return returns true if the payment can be processed, else false.
     */
    public static boolean processCheck(BankAccount account, double amount){
        try {
            account.deduct(amount);
            System.out.println("Payment processed successfully.");
            return true;
        } catch (InsufficientFundsException e) {
            //System.out.println("You have insufficient funds in your accounts.");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public String toString() {
        return "CheckProcessor";
    }

}
