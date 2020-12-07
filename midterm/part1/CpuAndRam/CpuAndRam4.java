package CpuAndRam;

/**
 * CpuAndRam. CpuAndRam with 4 cores. Represents the one ingredient of Phone.
 * 
 * @see Phone.
 */

public class CpuAndRam4 extends CpuAndRam {

    public CpuAndRam4(String info) {
        super(info);
    }

    @Override
    public String toString() {
        return "4 cores, " + super.toString();
    }

}
