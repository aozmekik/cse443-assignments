package Battery;

/**
 * BatteryBorron. Lithium Borron kind of Battery. Represents the one ingredient
 * of Phone.
 * 
 * @see Phone.
 */

public class BatteryBorron extends Battery {
    public BatteryBorron(String lifetimeAndVolt) {
        super(lifetimeAndVolt);
    }

    @Override
    public String toString() {
        return "Lithium-Boron, " + super.toString();
    }

}
