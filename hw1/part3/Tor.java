/**
 * <h1>Tor.</h1> Represents the Tor wearable gear.
 * <p>
 * 
 *
 * @author drh0use1
 * @version 1.0
 * @since 2020-11-21
 */


public class Tor implements Suit {

    /**
     * Returns the weight of Suit Tor in kgs.
     * @return Weight in kgs.
     */
    @Override
    public double weight() {
        return 50;
    }

    /**
     * Returns the cost of Suit Tor in TLs.
     * @return Cost in TLs.
     */
    @Override
    public int cost() {
        return 5000;
    }

    /**
     * Returns the description of Suit Tor.
     * @return Description related to Suit Tor.
     */
    @Override
    public String description() {
        return "Tor";
    }

}
