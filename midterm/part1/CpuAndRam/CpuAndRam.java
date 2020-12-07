package CpuAndRam;

/**
 * CpuAndRam. Represents the one ingredient of Phone.
 * 
 * @see Phone.
 */

public abstract class CpuAndRam {
    private String info;

    public CpuAndRam(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return info;
    }
}
