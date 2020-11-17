import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class LinearSolverDeluxe {
    private SystemSolver systemSolver;
    private Matrix system;

    public LinearSolverDeluxe() {

    }

    public void read() throws IOException {
        String content = "";

        try {
            content = new String(Files.readAllBytes(Paths.get("input.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] lines = content.split("\\r?\\n");

        system = new Matrix(lines.length, lines.length + 1);
        for (int i = 0; i < lines.length; i++) {
            parseLine(lines[i].replaceAll("\\s+", ""), i);
        }

    }

    public void solution() {
        // TODO. write solution to stdout.
    }

    // private void parseLine(String line, int x) {
    //     String[] items = line.split(",");
    //     for (int i = 0; i < items.length; i++)
    //         system.put(x, i, Integer.parseInt(items[i]));
    // }

    private void parseLine(String line, int x) throws IOException {
        int sign = 1;
        boolean finished = false;
        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            int co = 0;

            while (Character.isDigit(ch)) {
                co *= 10;
                co += Character.getNumericValue(ch);
                ch = line.charAt(i++);
            }

            if (finished)
                system.put(x, variables.size(), (co == 0 ? 1 : co) * sign);
            else if (Character.isLetter(ch)) {
                if (!variables.containsKey(ch))
                    variables.put(ch, variables.size());
                int y = variables.get(ch);
                system.put(x, y, system.get(x, y) + (co == 0 ? 1 : co) * sign);
            } else if (ch == '+')
                sign = 1;
            else if (ch == '-')
                sign = -1;
            else if (ch == '=')
                finished = true;
            else
                throw new IOException("Bad Input!\t Occured at char:" + ch);

        }
    }
}