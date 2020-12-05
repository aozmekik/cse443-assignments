package Battery;

public class BatteryCobalt extends Battery {
    public BatteryCobalt(String lifetimeAndVolt) {
        super(lifetimeAndVolt);
    }

    @Override
    public String toString()
    {
        return "Lithium-Cobalt, " + super.toString();
    }
    

}
