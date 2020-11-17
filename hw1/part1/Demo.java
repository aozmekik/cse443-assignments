public class Demo {
    public static void main(String[] args) {
        LinearSolverDeluxe solverDeluxe = new LinearSolverDeluxe(new GaussianElimination());

        try {

            solverDeluxe.read();
            solverDeluxe.solution();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
