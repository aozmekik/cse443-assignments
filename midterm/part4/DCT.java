
/**
 * DCT. Discrete Cosine Transform implementation.
 */

public class DCT extends TransformTemplate {

    @Override
    public void transform() {
        double factor = Math.PI / N;
        out = "";
        for (int i = 0; i < N; ++i) {
            double sum = 0;
            for (int j = 0; j < N; ++j)
                sum += numbers[j] * Math.cos((j + 0.5) * i * factor);
            out += String.format("%.4f\n", sum);
        }
    }

}