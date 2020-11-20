public class Demo {
    public static void main(String[] args) {
        String[] files = { "test/input0.txt", "test/input1.txt", "test/input2.txt", "test/input3.txt",
                "test/input4.txt", "test/input5.txt", };

        for (String file : files)
            testFile(file);
    }

    private static void testFile(String file) {
        SystemSolver gaussian = new GaussianElimination();
        SystemSolver inverse = new MatrixInversion();
        LinearSolverDeluxe solverDeluxe = new LinearSolverDeluxe(gaussian);
        try {

            solverDeluxe.read(file);
            solverDeluxe.solution();
            solverDeluxe.setSystemSolver(inverse);
            solverDeluxe.solution();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
