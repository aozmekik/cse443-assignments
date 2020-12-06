public class UpdateOperation implements Operation {
    private Database database;
    private int id;
    private int amount;

    public UpdateOperation(Database database, int id, int amount) {
        this.database = database;
        this.id = id;
        this.amount = amount;
    }

    @Override
    public boolean operate() {
        return database.update(id, amount);
    }

    @Override
    public boolean reverse() {
        return database.update(id, -amount);
    }

}
