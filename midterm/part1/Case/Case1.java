package Case;

public class Case1 extends Case {

    public Case1(String info) {
        super(info);
    }

    @Override
    public String spec() {
        return "Waterproof up to 1m, " + toString();
    }

}