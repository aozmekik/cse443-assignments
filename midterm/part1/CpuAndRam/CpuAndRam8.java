package CpuAndRam;

public class CpuAndRam8 extends CpuAndRam {

    public CpuAndRam8(String info) {
        super(info);
    }

    public String spec() {
        return "8 cores, " + toString();
    }

}
