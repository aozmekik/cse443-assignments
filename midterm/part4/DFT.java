
public class DFT extends TransformTemplate {

    @Override
    protected void transform() { // FIXME. change k & t.
        double[] real = new double[N];
        double[] imag = new double[N];

        for (int k = 0; k < N; ++k) {
            double realSum = 0;
            double imagSum = 0;
            for (int t = 0; t < N; ++t) {
                double angle = 2 * Math.PI * t * k / N;
                realSum += numbers[t] * Math.cos(angle);
                imagSum += -numbers[t] * Math.sin(angle);
            }

            real[k] = realSum;
            imag[k] = imagSum;
            out += String.format("%f + %fi\n", real[k], imag[k]);
        }

    }

}
