/**
 * Exception representing when an account has insufficeint funds to fulfill a payment
 * @author Zack Powers
 */
public class InsufficientFundsException extends Exception {

    public InsufficientFundsException(String msg) {
        super(msg);
    }
    
}
