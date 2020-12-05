package Battery;

public abstract class Battery {
    private String lifetimeAndVolt;

    public Battery(String lifetimeAndVolt) {
        this.lifetimeAndVolt = lifetimeAndVolt;
    }

    public abstract String spec();

}
