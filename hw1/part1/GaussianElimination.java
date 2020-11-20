
public class GaussianElimination implements SystemSolver {

    private static double[] gaussianSolve(double[][] A, double[] B) {
        int n = B.length;

        for (int p = 0; p < n; p++) {

            int max = p;
            for (int i = p + 1; i < n; i++) {
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }
            }
            double[] temp = A[p];
            A[p] = A[max];
            A[max] = temp;
            double t = B[p];
            B[p] = B[max];
            B[max] = t;

            if (A[p][p] == 0) {
                throw new IllegalArgumentException("Bad Matrix: Matrix is singular!");
            }

            for (int i = p + 1; i < n; i++) {
                double alpha = A[i][p] / A[p][p];
                B[i] -= alpha * B[p];
                for (int j = p; j < n; j++) {
                    A[i][j] -= alpha * A[p][j];
                }
            }
        }

        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (B[i] - sum) / A[i][i];
        }
        return x;
    }

    @Override
    public double[] solve(double[][] A) {
        int n = A.length;
        double a[][] = new double[n - 1][n - 1];
        double b[] = new double[n - 1];

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++)
                a[i][j] = A[i][j];
            b[i] = A[i][n - 1];
        }

        return gaussianSolve(a, b);
    }
}
