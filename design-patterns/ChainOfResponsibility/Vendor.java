/**
 * Vendor class
 * @author Zack Powers
 */

public class Vendor  implements PayingParty {
    
    public Vendor() {}
    
    @Override
    public void pay (double amount) {
        System.out.println("Vendor made payment.");
    }

    @Override
    public String toString() {
        return "Vendor";
    }
}
