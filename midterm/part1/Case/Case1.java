package Case;

/**
 * Case. Case which has waterproof up to 1m. Represents the one ingredient of
 * Phone.
 * 
 * @see Phone.
 */

public class Case1 extends Case {

    public Case1(String info) {
        super(info);
    }

    @Override
    public String toString() {
        return "Waterproof up to 1m, " + super.toString();
    }

}