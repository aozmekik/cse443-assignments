package Display;

public class Display32 extends Display {

    public Display32(String inch) {
        super(inch);
    }

    @Override
	public String spec() {
		return "32 bit," + toString();
	}

}
