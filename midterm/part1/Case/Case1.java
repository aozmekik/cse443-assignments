package Case;

public class Case1 extends Case {

    public Case1(String info) {
        super(info);
    }

    @Override
    public String toString() {
        return "Waterproof up to 1m, " + super.toString();
    }

}