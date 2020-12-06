public class SelectOperation implements Operation {
    private Database database;
    private int id;

    public SelectOperation(Database database, int id, boolean add) {
        this.database = database;
        this.id = id;
    }

    @Override
    public boolean operate(){
        return database.select(id);
    }

    @Override
    public boolean reverse() {
        return database.select(id);
    }
    
}
