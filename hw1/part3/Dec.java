/**
 * <h1>Dec.</h1> Represents the Dec wearable gear.
 * <p>
 * 
 *
 * @author drh0use1
 * @version 1.0
 * @since 2020-11-21
 */

public class Dec implements Suit {

    /**
     * Returns the weight of Suit Dec in kgs.
     * 
     * @return Weight in kgs.
     */
    @Override
    public double weight() {
        return 25.0;
    }

    /**
     * Returns the cost of Suit Dec in TLs.
     * 
     * @return Cost in TLs.
     */
    @Override
    public int cost() {
        return 500;
    }

    /**
     * Returns the description of Suit Dec.
     * 
     * @return Description related to Suit Dec.
     */
    @Override
    public String description() {
        return "Dec";
    }

}
