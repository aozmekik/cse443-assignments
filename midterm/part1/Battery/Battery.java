package Battery;

public abstract class Battery {
    private String info;

    public Battery(String lifetimeAndVolt) {
        this.info = lifetimeAndVolt;
    }

    @Override
    public String toString()
    {
        return info;
    }

}
