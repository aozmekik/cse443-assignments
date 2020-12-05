package Battery;

public class BatteryIon extends Battery {
    public BatteryIon(String lifetimeAndVolt) {
        super(lifetimeAndVolt);
    }
    public String spec()
    {
        return "Lithium-Ion, " + toString();
    }
    

}
