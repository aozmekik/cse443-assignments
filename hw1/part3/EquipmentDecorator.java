public abstract class EquipmentDecorator implements Suit {
    private Suit decoratedSuit;

    public EquipmentDecorator(Suit decoratedSuit) {
        this.decoratedSuit = decoratedSuit;
    }

    @Override
    public double weight() {
        return decoratedSuit.weight();
    }

    @Override
    public int cost() {
        return decoratedSuit.cost();
    }

    @Override
    public String description()
    {
        return decoratedSuit.description();
    }

}
