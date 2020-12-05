package CpuAndRam;

public class CpuAndRam2 extends CpuAndRam {

    public CpuAndRam2(String info) {
        super(info);
    }

    @Override
    public String toString() {
        return "2 cores, " + super.toString();
    }

}
