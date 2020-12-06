import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public abstract class TransformTemplate {
    protected double[] numbers = null;
    protected int N = 0;
    protected String out = null;

    protected void read(String file) throws IllegalArgumentException {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(file)));
        } catch (IOException e) {
            throw new IllegalArgumentException("Bad Input: " + e.toString());
        }

        String[] tokens = content.split("\\s+");

        N = tokens.length;
        numbers = new double[N];
        for (int i = 0; i < N; ++i)
            numbers[i] = Double.parseDouble(tokens[i]);
    }

    protected abstract void transform();

    protected void write(String file) {
        try {
            new File(file).createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(out + verbose());
            fileWriter.close();
            System.out.println("Done! Check " + file);
        } catch (IOException e) {
            System.out.println("An error occurred during writing of output.");
            e.printStackTrace();
        }
    }
    
    protected String verbose()
    {
        return "";
    }

    public final void transform(String inFile, String outFile) {
        read(inFile);
        Objects.requireNonNull(numbers);
        transform();
        Objects.requireNonNull(out);
        write(outFile);
    }

}