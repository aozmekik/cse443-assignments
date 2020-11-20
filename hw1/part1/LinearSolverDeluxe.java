import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * <h1>LinearSolverDeluxe.</h1> It admits as input from the user a system of
 * linear equations and outputs its solution, if it exists, or an error message
 * otherwise.
 * <p>
 * <b>Note:</b> Check Demo and input files for input format.
 * 
 * @see SystemSolver.java
 *
 * @author drh0use1
 * @version 1.0
 * @since 2020-11-21
 */

// TODO.
// demo
// test
// javadoc

public class LinearSolverDeluxe {
    private SystemSolver systemSolver;
    private double[][] matrix;

    /**
     * Creates a linear solver object with the given system solver method.
     * 
     * @param systemSolver Linear equation solver method. @see SystemSolver.java
     */
    public LinearSolverDeluxe(SystemSolver systemSolver) {
        this.systemSolver = systemSolver;
    }

    /**
     * Changes the linear system solver method dynamically in runtime.
     * 
     * @param systemSolver
     */
    public void setSystemSolver(SystemSolver systemSolver) {
        this.systemSolver = systemSolver;
    }

    /**
     * Reads the given input file containing linear system written in the proper
     * predefined format
     * 
     * @param file Input file containing linear system.
     * @see Demo.java and input.txt for the input format.
     */
    public void read(String file) throws IllegalArgumentException {
        String content = "";

        try {
            content = new String(Files.readAllBytes(Paths.get(file)));
        } catch (IOException e) {
            throw new IllegalArgumentException("Bad Input: " + e.toString());
        }

        String[] lines = content.split("\\r?\\n");

        matrix = new double[lines.length + 1][lines.length + 1];
        for (int i = 0; i < lines.length; i++) {
            parseLine(lines[i].replaceAll("\\s+", ""), i);
        }

    }

    /**
     * Prints the linear equation solution to screen.
     */
    public void solution() {
        double[] A = systemSolver.solve(matrix);
        System.out.printf("[");
        for (int i = 0; i < matrix.length - 1; i++)
            System.out.printf("%.4f" + (i + 1 < matrix.length - 1 ? ", " : ""), A[i]);
        System.out.println("]");
    }

    private void parseLine(String line, int x) {
        String[] items = line.split(",");
        if (matrix.length != items.length)
            throw new IllegalArgumentException("Bad Input: Row and column number does not match!");
        for (int i = 0; i < items.length; i++)
            matrix[x][i] = Integer.parseInt(items[i]);
    }

}