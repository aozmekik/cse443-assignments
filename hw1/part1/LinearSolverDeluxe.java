import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// TODO. list.
// refactor and stole them better.
// check solvability. let others check solvability? 
// write comment.

public class LinearSolverDeluxe {
    private SystemSolver systemSolver;
    private double[][] matrix;

    public LinearSolverDeluxe(SystemSolver systemSolver) {
        this.systemSolver = systemSolver;
    }

    public void read() {
        String content = "";

        try {
            content = new String(Files.readAllBytes(Paths.get("input.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] lines = content.split("\\r?\\n");

        matrix = new double[lines.length + 1][lines.length + 1];
        for (int i = 0; i < lines.length; i++) {
            parseLine(lines[i].replaceAll("\\s+", ""), i);
        }

    }

    public void solution() {
        double[] A = systemSolver.solve(matrix);
        System.out.printf("[");
        for (int i = 0; i < matrix.length - 1; i++)
            System.out.printf("%.4f" + (i + 1 < matrix.length - 1 ? ", " : ""), A[i]);
        System.out.println("]");
    }

    private void parseLine(String line, int x) {
        String[] items = line.split(",");
        for (int i = 0; i < items.length; i++)
            matrix[x][i] = Integer.parseInt(items[i]);
    }

}