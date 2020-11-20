/**
 * <h1>EquipmentDecorator.</h1> Decorator Design Pattern implementation.
 * EquipmentDecorater being the Decorators of Suits.
 * <p>
 * 
 * @see Suit.java
 *
 * @author drh0use1
 * @version 1.0
 * @since 2020-11-21
 */

public abstract class EquipmentDecorator implements Suit {
    private Suit decoratedSuit;

    /**
     * Decorates the given suit with this Decorator. a.k.a equips suit with 
     * this equipment.
     * @param decoratedSuit
     */
    public EquipmentDecorator(Suit decoratedSuit) {
        this.decoratedSuit = decoratedSuit;
    }

    /**
     * Returns the total weight of the Decorated Suit.
     * @return Weight of the Equipped Enchanced Suit.
     */
    @Override
    public double weight() {
        return decoratedSuit.weight();
    }

    /**
     * Returns the total cost of the Decorated Suit.
     * @return Cost of the Equipped Enchanced Suit.
     */
    @Override
    public int cost() {
        return decoratedSuit.cost();
    }

    /**
     * Returns the description of Decorated Suit.
     * @return Description of the Equipped Enchanced Suit. The followed pattern is
     * <suit>, <equipment1>, <equipment2>, <equipment3>, ... 
     */
    @Override
    public String description()
    {
        return decoratedSuit.description();
    }

}
