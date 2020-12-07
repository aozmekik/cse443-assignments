package CpuAndRam;

/**
 * CpuAndRam. CpuAndRam with 8 cores. Represents the one ingredient of Phone.
 * 
 * @see Phone.
 */

public class CpuAndRam8 extends CpuAndRam {

    public CpuAndRam8(String info) {
        super(info);
    }

    public String spec() {
        return "8 cores, " + super.toString();
    }

}
