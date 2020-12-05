package Storage;

public class Storage64 extends Storage {

    public Storage64(String info) {
        super(info);
    }

    @Override
    public String spec() {
        return "Max 64GB, " + toString();

    }
    
}
