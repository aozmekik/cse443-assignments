package Battery;

public class BatteryBorron extends Battery {
    public BatteryBorron(String lifetimeAndVolt) {
        super(lifetimeAndVolt);
    }
    
    @Override
    public String toString() {
        return "Lithium-Boron, " + super.toString();
    }

}
