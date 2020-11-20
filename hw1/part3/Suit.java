/**
 * <h1>Suit.</h1> Represents the wearable gear interface.
 * Should be able to give weight, cost and description related
 * to suit.
 * <p>
 * 
 *
 * @author drh0use1
 * @version 1.0
 * @since 2020-11-21
 */


public interface Suit {
    /**
     * Returns the weight of the suit in kgs.
     * @return Weight of the suit in kgs.
     */
    public double weight();

    /**
     * Returns the cost of the suit in kgs.
     * @return Cost of the suit in TLs.
     */
    public int cost();

    /**
     * Returns the description of the suit in kgs.
     * @return description of the suit in TLs.
     */
    public String description();
}