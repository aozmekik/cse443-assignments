/**
 * <h1>Demo.</h1> Demonstrates the LinearSolverDeluxe class.
 * <p>
 * 
 * @see LinearSolverDeluxe.
 *
 * @author drh0use1
 * @version 1.0
 * @since 2020-11-21
 */

public class Demo {
    public static void main(String[] args) {
        String[] files = { "test/input0.txt", "test/input1.txt", "test/input2.txt", "test/input3.txt",
                "test/input4.txt", };

        for (String file : files) {
            System.out.println("Testing on file: " + file);
            testFile(file);
        }
    }

    private static void testFile(String file) {
        SystemSolver gaussian = new GaussianElimination();
        SystemSolver inverse = new MatrixInversion();
        LinearSolverDeluxe solverDeluxe = new LinearSolverDeluxe(gaussian);
        try {

            solverDeluxe.read(file);
            System.out.print("with GaussianElimination:\t");
            solverDeluxe.solution();
            solverDeluxe.setSystemSolver(inverse);
            System.out.print("with InverseMatrix:\t\t");
            solverDeluxe.solution();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
