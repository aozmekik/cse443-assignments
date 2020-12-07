package Storage;

/**
 * Storage. Storage with 64bit. Represents the one ingredient of Phone.
 * 
 * @see Phone.
 */

public class Storage64 extends Storage {

    public Storage64(String info) {
        super(info);
    }

    @Override
    public String toString() {
        return "Max 64GB, " + super.toString();

    }

}
