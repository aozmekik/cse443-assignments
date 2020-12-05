package Case;

public class Case2 extends Case {

    public Case2(String info) {
        super(info);
    }

    @Override
    public String spec() {
        return "Waterproof up to 2m, " + toString();
    }

}