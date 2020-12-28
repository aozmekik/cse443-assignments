import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ProxyTable implements ITable {
    private DataBaseTable table;
    ReadWriteLock readWriteLock[];

    public ProxyTable(int row, int col) {
        table = new DataBaseTable(row, col);
        readWriteLock = new ReentrantReadWriteLock[table.getNumberOfRows()];

        for (int i = 0; i < table.getNumberOfRows(); ++i)
            readWriteLock[i] = new ReentrantReadWriteLock();
    }

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

    @Override
    public void setElementAt(int row, int column, Object o) {
        System.out.println("[WRITE] Waiting row: " + row);
        readWriteLock[row].writeLock().lock();
        System.out.println("[WRITE] Locked row: " + row);
        table.setElementAt(row, column, o);
        readWriteLock[row].writeLock().unlock();
        System.out.println("[WRITE] Unlocked row: " + row);

    }

    @Override
    public int getNumberOfRows() {
        return table.getNumberOfRows();
    }

    @Override
    public int getNumberOfColumns() {
        return table.getNumberOfColumns();
    }

}
