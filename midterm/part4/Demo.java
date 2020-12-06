public class Demo {
    public static void main(String[] args) {
        TransformTemplate dft = new DFT();
        dft.transform("test.txt", "output.txt");

        TransformTemplate dct = new DCT();

        dct.transform("test.txt", "output2.txt");
    }
}
