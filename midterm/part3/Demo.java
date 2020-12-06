public class Demo {
    public static void main(String[] args) {
        Database database = new Database();

        DatabaseManager manager = new DatabaseManager();
        
        manager.takeOperation(new AlterOperation(database, 1, true));
        manager.takeOperation(new UpdateOperation(database, 1, 200));
        manager.takeOperation(new UpdateOperation(database, 1, 200));

        manager.takeOperation(new UpdateOperation(database, 2, 200));

        manager.performOperations();

    }
}
