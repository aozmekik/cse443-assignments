public class PhoneFactory extends AbstractPhoneFactory {
    public enum Region {
        TURKEY, EU, GLOBAL
    }

    private PhoneIngredientFactory ingredientFactory;

    public PhoneFactory(Region region) {
        switch (region) {
            case TURKEY:
                ingredientFactory = new TurkeyPhoneIngredientFactory();
                break;
            case EU:
                ingredientFactory = new EUPhoneIngredientFactory();
                break;
            case GLOBAL:
                ingredientFactory = new GlobalPhoneIngredientFactory();
                break;
        }
    }

    @Override
    protected Phone preparePhone(String model) {
        Phone phone = null;
        if (model.equalsIgnoreCase("maximumeffort"))
            phone = new MaximumEffortPhone(ingredientFactory);
        else if (model.equalsIgnoreCase("iflasdeluxe"))
            phone = new IflasDeluxePhone(ingredientFactory);
        else if (model.equalsIgnoreCase("iiamaniflas"))
            phone = new IIAmanIflasPhone(ingredientFactory);
        return phone;
    }
}
