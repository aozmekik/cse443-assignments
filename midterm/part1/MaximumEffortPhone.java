/**
 * MaximumEffortPhone. Represents the one kind of a phone, a model which has
 * different kind of ingredient specifications.
 * 
 * @see Phone.
 */

public class MaximumEffortPhone extends Phone {
    private PhoneIngredientFactory ingredientFactory;

    public MaximumEffortPhone(PhoneIngredientFactory ingredientFactory) {
        super("MaximumEffort");
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    public void prepare() {
        System.out.println("\t>Preparing " + name);
        display = ingredientFactory.createDisplay("5.5 inches");
        battery = ingredientFactory.createBattery("27h, 3600mAh");
        cpuAndRam = ingredientFactory.createCpuAndRam("2.8GHz, 8GB");
        storage = ingredientFactory.createStorage("MicroSD support, 64GB");
        camera = ingredientFactory.createCamera("12Mp front, 8Mp rear");
        safe = ingredientFactory.createCase("151x73x7.7mm, dustproof, waterproof, aluminum");
    }

}