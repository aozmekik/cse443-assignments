import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

        // TODO. check solvability.
    }

    public void solution() {
        systemSolver.solve(matrix);
        String sol = "[";
        for (int i = 0; i < matrix.length - 1; i++)
            sol += Double.toString(matrix[matrix.length - 1][i]) + (i + 1 < matrix.length - 1? ", " : "");
        sol += "]";
        System.out.println(sol);
    }

    private void parseLine(String line, int x) {
        String[] items = line.split(",");
        for (int i = 0; i < items.length; i++)
            matrix[x][i] = Integer.parseInt(items[i]);
    }

}