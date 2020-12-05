package Display;

public abstract class Display {
    private String inch;

    public Display(String inch) {
        this.inch = inch;
    }

    @Override
    public String toString() {
        return this.inch;
    }
}
