package Case;

/**
 * Case. Represents the one ingredient of Phone.
 * 
 * @see Phone.
 */

public abstract class Case {
    private String info;

    public Case(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return info;
    }
}
