package Battery;

/**
 * BatteryIon. Lithium Ion kind of Battery. Represents the one ingredient of
 * Phone.
 * 
 * @see Phone.
 */

public class BatteryIon extends Battery {
    public BatteryIon(String lifetimeAndVolt) {
        super(lifetimeAndVolt);
    }

    @Override
    public String toString() {
        return "Lithium-Ion, " + super.toString();
    }

}
