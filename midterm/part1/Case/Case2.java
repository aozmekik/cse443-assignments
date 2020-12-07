package Case;

/**
 * Case. Case which has waterproof up to 2m. Represents the one ingredient of
 * Phone.
 * 
 * @see Phone.
 */

public class Case2 extends Case {

    public Case2(String info) {
        super(info);
    }

    @Override
    public String toString() {
        return "Waterproof up to 2m, " + super.toString();
    }

}