package Display;

/**
 * Display. Display with 32bit. Represents the one ingredient of Phone.
 * 
 * @see Phone.
 */

public class Display32 extends Display {

    public Display32(String inch) {
        super(inch);
    }

    @Override
    public String toString() {
        return "32 bit," + super.toString();
    }

}
