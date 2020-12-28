import java.util.concurrent.locks.ReadWriteLock;

public class ProxyTable implements ITable {
    private DataBaseTable table;
    ReadWriteLock readWriteLock[];

    public ProxyTable() {
        table = new DataBaseTable();

        readWriteLock = new ReadWriteLock[table.getNumberOfRows()];
    }

    @Override
    public Object getElementAt(int row, int column) {
        readWriteLock[row].readLock().lock();
        Object object = table.getElementAt(row, column);
        readWriteLock[row].readLock().unlock();
        return object;
    }

    @Override
    public void setElementAt(int row, int column, Object o) {
        readWriteLock[row].writeLock().lock();
        table.setElementAt(row, column, o);
        readWriteLock[row].writeLock().unlock();
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
