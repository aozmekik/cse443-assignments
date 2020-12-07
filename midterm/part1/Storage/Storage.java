package Storage;

/**
 * Storage. Represents the one ingredient of Phone.
 * 
 * @see Phone.
 */

public abstract class Storage {
    private String info;

    public Storage(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return info;
    }

}
