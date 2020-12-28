public class Driver {
    public static void test(ITable table) {
        table.setElementAt(3, 3, 1);
        System.out.println(table.getElementAt(3, 3));
        // table.setElementAt(3, 3, 2);
        // System.out.println(table.getElementAt(3, 3));
        // table.setElementAt(3, 3, 3);
        // System.out.println(table.getElementAt(3, 3));
    }

    public static void testProxies(ITable table) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test(table);
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                test(table);
            }
        });

        thread1.start();
        thread2.start();
        test(table);
    }

    public static void main(String[] args) {
        ITable table1 = new ProxyTable(5, 5);
        ITable table2 = new ProxyTable2(5, 5);

        System.out.println("Testing proxy table #1");
        testProxies(table1);
        System.out.println("Testing proxy table #2");
        testProxies(table2);

    }
}
