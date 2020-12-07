/**
 * IIAmanIflas. Represents the one kind of a phone, a model which has
 * different kind of ingredient specifications.
 * 
 * @see Phone.
 */

public class IIAmanIflasPhone extends Phone {
    private PhoneIngredientFactory ingredientFactory;

    public IIAmanIflasPhone(PhoneIngredientFactory ingredientFactory) {
        super("IIAmanIflas");
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    public void prepare() {
        System.out.println("\t>Preparing " + name);
        display = ingredientFactory.createDisplay("4.5 inches");
        battery = ingredientFactory.createBattery("16h, 2000mAh");
        cpuAndRam = ingredientFactory.createCpuAndRam("2.2GHz, 4GB");
        storage = ingredientFactory.createStorage("MicroSD support, 16GB");
        camera = ingredientFactory.createCamera("8Mp front, 5Mp rear");
        safe = ingredientFactory.createCase("143x69x7.3mm, waterproof, plastic");
    }

}