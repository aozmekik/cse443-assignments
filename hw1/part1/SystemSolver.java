/**
 * <h1>SystemSolver.</h1> SystemSolver interface to implement Strategy Design
 * Pattern.
 * <p>
 * 
 * @see SystemSolver.java
 *
 * @author drh0use1
 * @version 1.0
 * @since 2020-11-21
 */

public interface SystemSolver {
    /**
     * Solves the given system of linear equations and returns the solution
     * 
     * @param A System of linear equations in the matrix format. Matrix is in the
     *          typical format of linear algebra matrix.: | <a1><x1>, <a2><x2>,
     *          <a3><x3>, ..., k1 | | <b1><x1>, <b2><x2>, <b3><x3>, ..., k2 |
     * 
     * @return Solution. Format (Placeholder variables represents the actual values
     *         of calculated variables): [x1, x2, x3, ...]
     * @throws IllegalArgumentException If the system is not solvable, throws an
     *                                  exception.
     */
    double[] solve(double[][] A) throws IllegalArgumentException;
}
