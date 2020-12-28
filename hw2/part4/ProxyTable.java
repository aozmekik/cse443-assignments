
/**
 * Basic implementation of Proxy Design Pattern.
 * 
 * Encapsulates the DataBaseTable implementation and adds a sync feature
 * (i.e. read write sync) for it.
 * Represents the requested implementation of Question4.a, i.e. reader writer
 * sync solve is applied in this proxy table implementation, with using the
 * java util predefined ReadWriteLocks.
 */

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ProxyTable implements ITable {
    private DataBaseTable table;
    ReadWriteLock readWriteLock[];

    /**
     * Creates a ProxyTable with the given dims.
     */
    public ProxyTable(int row, int col) {
        table = new DataBaseTable(row, col);
        readWriteLock = new ReentrantReadWriteLock[table.getNumberOfRows()];

        for (int i = 0; i < table.getNumberOfRows(); ++i)
            readWriteLock[i] = new ReentrantReadWriteLock();
    }

    /**
     * Gets the element at given dims, thread safely.
     */
    @Override
    public Object getElementAt(int row, int column) {
        System.out.println("[READ] Waiting row: " + row);
        readWriteLock[row].readLock().lock();
        System.out.println("[READ] Locked row: " + row);
        Object object = table.getElementAt(row, column);
        readWriteLock[row].readLock().unlock();
        System.out.println("[READ] Unlocked row: " + row);
        return object;
    }

    /**
     * Sets the element at given dims, thread safely.
     */
    @Override
    public void setElementAt(int row, int column, Object o) {
        System.out.println("[WRITE] Waiting row: " + row);
        readWriteLock[row].writeLock().lock();
        System.out.println("[WRITE] Locked row: " + row);
        table.setElementAt(row, column, o);
        readWriteLock[row].writeLock().unlock();
        System.out.println("[WRITE] Unlocked row: " + row);

    }

    /**
     * Gets the number of rows in the table.
     */
    @Override
    public int getNumberOfRows() {
        return table.getNumberOfRows();
    }

    /**
     * Gets the number of columns in the table.
     */
    @Override
    public int getNumberOfColumns() {
        return table.getNumberOfColumns();
    }

}
