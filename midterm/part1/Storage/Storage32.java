package Storage;

/**
 * Storage. Storage with 32GB. Represents the one ingredient of Phone.
 * 
 * @see Phone.
 */

public class Storage32 extends Storage {

    public Storage32(String info) {
        super(info);
    }

    @Override
    public String toString() {
        return "Max 32GB, " + super.toString();
    }

}
