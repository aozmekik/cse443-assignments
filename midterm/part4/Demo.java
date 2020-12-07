/**
 * Demonstration of the Template Design Pattern.
 */

public class Demo {
    public static void main(String[] args) {
        TransformTemplate dft = new DFT();
        dft.transform("test.txt", "output0.txt");
        dft = new DFT(true);
        dft.transform("test.txt", "output1.txt");

        TransformTemplate dct = new DCT();

        dct.transform("test.txt", "output2.txt");
        
    }
}
