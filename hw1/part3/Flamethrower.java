public class Flamethrower extends EquipmentDecorator {

    public Flamethrower(Suit decoratedSuit) {
        super(decoratedSuit);
    }

    @Override
    public double weight() {
        return super.weight() + 2;
    }

    @Override
    public int cost() {
        return super.cost() + 50; 
    }

    @Override
    public String description() {
        return super.description() + ", Flamethrower";
    }
}
