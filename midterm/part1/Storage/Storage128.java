package Storage;

public class Storage128 extends Storage {

    public Storage128(String info) {
        super(info);
    }

    @Override
    public String toString() {
        return "Max 128GB, " + super.toString();
    }

}
