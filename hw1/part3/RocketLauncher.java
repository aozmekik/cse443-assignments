public class RocketLauncher extends EquipmentDecorator {

    public RocketLauncher(Suit decoratedSuit) {
        super(decoratedSuit);
    }

    @Override
    public double weight() {
        return super.weight() + 7.5;
    }

    @Override
    public int cost() {
        return super.cost() + 150; 
    }


    @Override
    public String description() {
        return super.description() + ", RocketLauncher";
    }
}
