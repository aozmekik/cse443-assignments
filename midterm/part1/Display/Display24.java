package Display;

public class Display24 extends Display {

    public Display24(String inch) {
        super(inch);
    }

    @Override
	public String spec() {
		return "24 bit," + toString();
	}

}
