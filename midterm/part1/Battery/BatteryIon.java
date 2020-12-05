package Battery;

public class BatteryIon extends Battery {
    public BatteryIon(String lifetimeAndVolt) {
        super(lifetimeAndVolt);
    }

    @Override
    public String toString()
    {
        return "Lithium-Ion, " + super.toString();
    }
    

}
