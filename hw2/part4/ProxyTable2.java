import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ProxyTable2 implements ITable {
    private DataBaseTable table;
    ReadWriteLock readWriteLock[];

    private int readers = 0;
    private int writers = 0;
    private int writeRequests = 0;

    public ProxyTable2(int row, int col) {
        table = new DataBaseTable(row, col);
        readWriteLock = new ReentrantReadWriteLock[table.getNumberOfRows()];

        for (int i = 0; i < table.getNumberOfRows(); ++i)
            readWriteLock[i] = new ReentrantReadWriteLock();
    }

    @Override
    public Object getElementAt(int row, int column) {
        try {
            System.out.println("[READ] Waiting row: " + row);
            lockRead();
            System.out.println("[READ] Locked row: " + row);
            Object object = table.getElementAt(row, column);
            unlockRead();
            System.out.println("[READ] Unlocked row: " + row);
            return object;
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void setElementAt(int row, int column, Object o) {
        try {
            System.out.println("[WRITE] Waiting row: " + row);
            lockWrite();
            System.out.println("[WRITE] Locked row: " + row);
            table.setElementAt(row, column, o);
            unlockWrite();
            System.out.println("[WRITE] Unlocked row: " + row);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    @Override
    public int getNumberOfRows() {
        return table.getNumberOfRows();
    }

    @Override
    public int getNumberOfColumns() {
        return table.getNumberOfColumns();
    }

    public synchronized void lockRead() throws InterruptedException {
        while (writers > 0 || writeRequests > 0) {
            wait();
        }
        readers++;
    }

    public synchronized void unlockRead() {
        readers--;
        notifyAll();
    }

    public synchronized void lockWrite() throws InterruptedException {
        writeRequests++;

        while (readers > 0 || writers > 0) {
            wait();
        }
        writeRequests--;
        writers++;
    }

    public synchronized void unlockWrite() throws InterruptedException {
        writers--;
        notifyAll();
    }

}
