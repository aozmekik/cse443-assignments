
public class DFT extends TransformTemplate {

    @Override
    protected void transform() {
        out = "";

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

    }

}
