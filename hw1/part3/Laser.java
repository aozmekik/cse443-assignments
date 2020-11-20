/**
 * <h1>Laser.</h1> One of the many instances of the decorator type. Laser, is an
 * equipment which can be equipped in the Suit.
 * <p>
 * 
 * @see EquipmentDecorator.java
 * @see Suit.java
 *
 * @author drh0use1
 * @version 1.0
 * @since 2020-11-21
 */
public class Laser extends EquipmentDecorator {

    /**
     * Decorates the given suit with Laser.
     * 
     * @param decoratedSuit
     */
    public Laser(Suit decoratedSuit) {
        super(decoratedSuit);
    }

    /**
     * Returns the total weight of the Decorated Suit.
     * 
     * @return Weight of the Equipped Enchanced Suit.
     */
    @Override
    public double weight() {
        return super.weight() + 5.5;
    }

    /**
     * Returns the total cost of the Decorated Suit.
     * 
     * @return Cost of the Equipped Enchanced Suit.
     */
    @Override
    public int cost() {
        return super.cost() + 200;
    }

    /**
     * Returns the description of Decorated Suit.
     * 
     * @return Description of the Equipped Enchanced Suit. The followed pattern is
     *         <suit>, <equipment1>, <equipment2>, , ..., + ", Laser"
     */
    @Override
    public String description() {
        return super.description() + ", Laser";
    }

}
