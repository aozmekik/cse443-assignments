public class Matrix {
    // TODO. a quick test on this one.

    public Matrix(int x, int y) {
        this.x = x;
        this.y = y;
        table = new int[this.x][this.y];

        /* initialize table with zeros */
        for (int i = 0; i < this.x; i++)
            for (int j = 0; j < this.y; j++)
                table[i][j] = 0;
    }

    public void put(int i, int j, int n) {
        table[i][j] = n;
    }

    public int get(int i, int j) {
        return table[i][j];
    }

    private int x; // length of matrix in x dim.
    private int y; // length of matrix in y dim.
    private int table[][]; // matrix table.
}
