package Storage;

public abstract class Storage {
    private String info;

    public Storage(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return info;
    }

    public abstract String spec();

}
