package Battery;

public class BatteryCobalt extends Battery {
    public BatteryCobalt(String lifetimeAndVolt) {
        super(lifetimeAndVolt);
    }
    public String spec()
    {
        return "Lithium-Cobalt, " + toString();
    }
    

}
