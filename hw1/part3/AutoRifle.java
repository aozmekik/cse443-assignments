public class AutoRifle extends EquipmentDecorator {

    public AutoRifle(Suit decoratedSuit) {
        super(decoratedSuit);
    }

    @Override
    public double weight() {
        return super.weight() + 1.5;
    }

    @Override
    public int cost() {
        return super.cost() + 30;
    }

    @Override
    public String description() {
        return super.description() + ", AutoRifle";
    }

}
