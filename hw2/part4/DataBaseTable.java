
/**
 * Possible DataBaseTable implementation.
 * 
 * The data structure mentioned in the assignment was implemented in a very
 * simple way in order to be able to test it.
 */

public class DataBaseTable implements ITable {
    int row, col;
    int[][] table;

    /**
     * Creates a DataBaseTable with given row and col values.
     * 
     * @param row row length of table.
     * @param col col length of table.
     */
    public DataBaseTable(int row, int col) {
        this.row = row;
        this.col = col;
        table = new int[row][col];
    }

    /**
     * Gets element at (row, column) in the table.
     */
    @Override
    public Object getElementAt(int row, int column) {
        return table[row][column];
    }

    /**
     * Sets element at (row, column) in the table with the given object.
     */
    @Override
    public void setElementAt(int row, int column, Object o) {
        table[row][column] = (int) o;
    }

    /**
     * Gets the row size of the table.
     */
    @Override
    public int getNumberOfRows() {
        return row;
    }

    /**
     * Gets the column size of the table.
     */
    @Override
    public int getNumberOfColumns() {
        return col;
    }

}
