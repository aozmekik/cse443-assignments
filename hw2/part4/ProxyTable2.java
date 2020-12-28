
/**
 * Basic implementation of Proxy Design Pattern.
 * 
 * Encapsulates the DataBaseTable implementation and adds a sync feature (i.e.
 * read write sync) for it. Represents the requested implementation of
 * Question4.b, i.e. reader writer sync solve with prioritizing writers is
 * applied in this proxy table implementation.
 */

public class ProxyTable2 implements ITable {
    private DataBaseTable table;

    private int readerCount = 0;
    private int writerCount = 0;
    private int writeRequests = 0;

    public ProxyTable2(int row, int col) {
        table = new DataBaseTable(row, col);
    }

    @Override
    public Object getElementAt(int row, int column) {
        try {
            System.out.println("[READ] Waiting row: " + row);
            readLock();
            System.out.println("[READ] Locked row: " + row);
            Object object = table.getElementAt(row, column);
            readUnlock();
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
            writeLock();
            System.out.println("[WRITE] Locked row: " + row);
            table.setElementAt(row, column, o);
            writeUnlock();
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

    public synchronized void readLock() throws InterruptedException {
        while (writerCount > 0 || writeRequests > 0) {
            wait();
        }
        readerCount++;
    }

    public synchronized void readUnlock() {
        readerCount--;
        notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        writeRequests++;

        while (readerCount > 0 || writerCount > 0) {
            wait();
        }
        writeRequests--;
        writerCount++;
    }

    public synchronized void writeUnlock() throws InterruptedException {
        writerCount--;
        notifyAll();
    }

}
