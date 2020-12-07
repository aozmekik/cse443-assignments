
/**
 * IflasDeluxePhone. Represents the one kind of a phone, a model which has
 * different kind of ingredient specifications.
 * @see Phone.
 */

public class IflasDeluxePhone extends Phone {
    private PhoneIngredientFactory ingredientFactory;

    public IflasDeluxePhone(PhoneIngredientFactory ingredientFactory) {
        super("IflasDeluxe");
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    public void prepare() {
        System.out.println("\t>Preparing " + name);
        display = ingredientFactory.createDisplay("5.3 inches");
        battery = ingredientFactory.createBattery("20h, 2800mAh");
        cpuAndRam = ingredientFactory.createCpuAndRam("2.2GHz, 6GB");
        storage = ingredientFactory.createStorage("MicroSD support, 32GB");
        camera = ingredientFactory.createCamera("12Mp front, 5Mp rear");
        safe = ingredientFactory.createCase("149x73x7.7mm, waterproof, aliminum");
    }

}