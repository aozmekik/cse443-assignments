
public class DFT extends TransformTemplate {
    private double elapsedTime;
    private boolean print;

    public DFT(boolean print) {
        this.print = print;
    }

    public DFT() {
        print = false;
    }

    @Override
    protected void transform() {
        out = "";
        long start = System.nanoTime();
        for (int i = 0; i < N; ++i) {
            double realSum = 0;
            double imagSum = 0;
            for (int j = 0; j < N; ++j) {
                double angle = 2 * Math.PI * j * i / N;
                realSum += numbers[j] * Math.cos(angle);
                imagSum += -numbers[j] * Math.sin(angle);
            }
            out += String.format("%.4f + %.4fi\n", realSum, imagSum);
        }
        long stop = System.nanoTime();
        elapsedTime = stop - start;

    }

    @Override
    protected String verbose() {
        String str = "";
        if (print)
            str = String.format("Elapsed Time in milliseconds: %f", elapsedTime / 1000000);
        return str;
    }

}
