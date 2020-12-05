package Case;

public class Case2 extends Case {

    public Case2(String info) {
        super(info);
    }

    @Override
    public String toString() {
        return "Waterproof up to 2m, " + super.toString();
    }

}