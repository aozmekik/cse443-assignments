public class AlterOperation implements Operation {
    private Database database;
    private int id;
    private boolean add;

    public AlterOperation(Database database, int id, boolean add) {
        this.database = database;
        this.id = id;
        this.add = add;
    }

    @Override
    public boolean operate() {
        return database.alter(id, add);
    }

    @Override
    public boolean reverse() {
        return database.alter(id, !add);
    }

}
