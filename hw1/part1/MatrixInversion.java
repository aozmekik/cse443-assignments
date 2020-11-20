/**
 * <h1>MatrixInversion.</h1> Inversion method implementing SystemSolver
 * interface to solve linear equation system.
 * <p>
 * 
 * @see SystemSolver.java
 *
 * @author drh0use1
 * @version 1.0
 * @since 2020-11-21
 */

public class MatrixInversion implements SystemSolver {
    private static int N = 0;

    /**
     * Multiplicator for matrices. Multiplies matrix A and B, returns the result
     * matrix.
     * 
     * @param A First matrix operand.
     * @param B Second matrix operand.
     * @return Result matrix.
     */
    private static double[][] mult(double[][] A, double[][] B) {
        int Ax = A.length;
        int Ay = A[0].length;
        int Bx = B.length;
        int By = B[0].length;

        if (Ay != Bx)
            throw new IllegalArgumentException("Bad Matrix: Dims does not match!");

        // initialize result matrix.
        double[][] C = new double[Ax][By];
        for (int i = 0; i < Ax; i++)
            for (int j = 0; j < By; j++)
                C[i][j] = 0;

        // multiply them.
        for (int i = 0; i < Ax; i++)
            for (int j = 0; j < By; j++)
                for (int k = 0; k < Ay; k++)
                    C[i][j] += A[i][k] * B[k][j];

        return C;
    }

    /**
     * Cofactor method. This method is a helper/utility method for both
     * in the calculation of determinant and adjoint matrix. It collects the
     * cofactors in the matrix. 
     */
    private static void co(double A[][], double B[][], int x, int y, int n) {
        int i = 0, j = 0;

        // collect cofactor from matrix.
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row != x && col != y) {
                    B[i][j] = A[row][col];
                    j++;

                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    /**
     * Finds the determinant of matrix recursively.
     * @param A Input matrix.
     * @return n Determinant.
     */
    private static double det(double A[][], int n) {
        int determinant = 0;
        if (n == 1)
            return A[0][0];

        double[][] temp = new double[N][N];

        int sign = 1;

        // find determinant of submatrixes recursively.
        for (int i = 0; i < n; i++) {
            co(A, temp, 0, i, n); // collect cofactor matrix.
            determinant += sign * A[0][i] * det(temp, n - 1);
            sign = -sign;
        }

        return determinant;
    }

    /**
     * Returns the adjoint of the given matrix.
     * @param A Input matrix.
     * @return Adjoint.
     */
    private static double[][] adj(double A[][]) {
        double[][] adjoint = new double[N][N];
        if (N == 1) {
            adjoint[0][0] = 1;
            return adjoint;
        }

        int sign = 1;
        double[][] temp = new double[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                co(A, temp, i, j, N);
                sign = ((i + j) % 2 == 0) ? 1 : -1;
                adjoint[j][i] = (sign) * det(temp, N - 1);
            }
        }

        return adjoint;
    }

    /**
     * Gets the inverse of given matrix.
     * @param A Input matrix.
     * @return Inverse of A.
     */
    private static double[][] inv(double A[][]) {
        double[][] inverse = new double[A.length][A.length];
        double det = det(A, N);
        if (det == 0)
            throw new IllegalArgumentException("Bad Matrix: Matrix is singular!");

        double[][] adjoint = adj(A);

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                inverse[i][j] = adjoint[i][j] / (double) det;

        return inverse;
    }

    /**
     * Solves the linear equation with the matrix inversion method.
     * @param A Input matrix.
     * @return Solution.
     * @see SystemSolver.java
     */
    @Override
    public double[] solve(double[][] A) {
        N = A.length;
        double[][] B = new double[N - 1][N - 1];
        double[][] C = new double[N - 1][1];

        for (int i = 0; i < N - 1; ++i)
            for (int j = 0; j < N - 1; ++j)
                B[i][j] = A[i][j];

        for (int i = 0; i < N - 1; ++i)
            C[i][0] = A[i][N - 1];

        N = B.length;

        double[][] D = mult(inv(B), C);

        double[] result = new double[D.length];
        for (int i = 0; i < D.length; ++i)
            result[i] = D[i][0];
        return result;
    }

}