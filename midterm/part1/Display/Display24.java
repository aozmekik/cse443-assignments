package Display;

public class Display24 extends Display {

    public Display24(String inch) {
        super(inch);
    }

    @Override
	public String toString() {
		return "24 bit," + super.toString();
	}

}
