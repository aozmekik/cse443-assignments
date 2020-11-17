public class Laser extends EquipmentDecorator {

    public Laser(Suit decoratedSuit) {
        super(decoratedSuit);
    }

    @Override
    public double weight() {
        return super.weight() + 5.5;
    }

    @Override
    public int cost() {
        return super.cost() + 200; 
    }

    @Override
    public String description() {
        return super.description() + ", Laser";
    }

}
