package Display;

/**
 * Display. Represents the one ingredient of Phone.
 * 
 * @see Phone.
 */

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
