package Battery;

/**
 * BatteryCobalt. Lithium Cobalt kind of Battery. Represents the one ingredient
 * of Phone.
 * 
 * @see Phone.
 */

public class BatteryCobalt extends Battery {
    public BatteryCobalt(String lifetimeAndVolt) {
        super(lifetimeAndVolt);
    }

    @Override
    public String toString() {
        return "Lithium-Cobalt, " + super.toString();
    }

}
