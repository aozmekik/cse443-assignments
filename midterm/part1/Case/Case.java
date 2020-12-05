package Case;

public abstract class Case {
    private String info;

    public Case(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return info;
    }

    public abstract String spec();
}
