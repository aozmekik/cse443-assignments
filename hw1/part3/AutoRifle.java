/**
 * <h1>AutoRifle.</h1> One of the many instances of the decorator type.
 * AutoRifle, is an equipment which can be equipped in the Suit.
 * <p>
 * 
 * @see EquipmentDecorator.java
 * @see Suit.java
 *
 * @author drh0use1
 * @version 1.0
 * @since 2020-11-21
 */

public class AutoRifle extends EquipmentDecorator {

    /**
     * Decorates the given suit with AutoRifle.
     * 
     * @param decoratedSuit
     */
    public AutoRifle(Suit decoratedSuit) {
        super(decoratedSuit);
    }

    /**
     * Returns the total weight of the Decorated Suit.
     * 
     * @return Weight of the Equipped Enchanced Suit.
     */
    @Override
    public double weight() {
        return super.weight() + 1.5;
    }

    /**
     * Returns the total cost of the Decorated Suit.
     * 
     * @return Cost of the Equipped Enchanced Suit.
     */
    @Override
    public int cost() {
        return super.cost() + 30;
    }

    /**
     * Returns the description of Decorated Suit.
     * 
     * @return Description of the Equipped Enchanced Suit. The followed pattern is
     *         <suit>, <equipment1>, <equipment2>, , ..., + ", AutoRifle"
     */
    @Override
    public String description() {
        return super.description() + ", AutoRifle";
    }

}
