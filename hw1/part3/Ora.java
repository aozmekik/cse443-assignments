/**
 * <h1>Ora.</h1> Represents the Ora wearable gear.
 * <p>
 * 
 *
 * @author drh0use1
 * @version 1.0
 * @since 2020-11-21
 */

public class Ora implements Suit {

    /**
     * Returns the weight of Suit Ora in kgs.
     * @return Weight in kgs.
     */
    @Override
    public double weight() {
        return 30.0;
    }

    /**
     * Returns the cost of Suit Ora in TLs.
     * @return Cost in TLs.
     */
    @Override
    public int cost() {
        return 1500;
    }

    /**
     * Returns the description of Suit Ora.
     * @return Description related to Suit Ora.
     */
    @Override
    public String description() {
        return "Ora";
    }

}
