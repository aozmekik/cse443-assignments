package CpuAndRam;

public class CpuAndRam2 extends CpuAndRam {

    public CpuAndRam2(String info) {
        super(info);
    }

    public String spec() {
        return "2 cores, " + toString();
    }

}
