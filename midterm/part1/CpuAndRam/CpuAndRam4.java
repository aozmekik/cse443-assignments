package CpuAndRam;

public class CpuAndRam4 extends CpuAndRam {

    public CpuAndRam4(String info) {
        super(info);
    }

    public String spec() {
        return "4 cores, " + toString();
    }

}
