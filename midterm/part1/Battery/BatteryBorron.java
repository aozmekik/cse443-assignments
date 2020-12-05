package Battery;

public class BatteryBorron extends Battery {
    public BatteryBorron(String lifetimeAndVolt) {
        super(lifetimeAndVolt);
    }
    public String spec()
    {
        return "Lithium-Boron, " + toString();
    }

}
