package Case;

/**
 * Case. Case which has waterproof up to 50cm. Represents the one ingredient of
 * Phone.
 * 
 * @see Phone.
 */

public class Case50 extends Case {

    public Case50(String info) {
        super(info);
    }

    @Override
    public String toString() {
        return "Waterproof up to 50cm, " + super.toString();
    }

}