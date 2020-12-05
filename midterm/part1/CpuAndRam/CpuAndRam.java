package CpuAndRam;

public abstract class CpuAndRam {
    private String info;

    public CpuAndRam(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return info;
    }
    
    public abstract String spec();

}
