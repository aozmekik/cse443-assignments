import java.util.HashMap;

public class Database {
    private HashMap<Integer, Integer> db = new HashMap<Integer, Integer>();

    public boolean update(int id, int amount) {
        boolean fail = false;
        if (db.containsKey(id)) {
            int value = db.get(id);
            System.out.printf("UPDATE: Updated row %d with +%d to database. (Old value: %d, New Value: %d)\n", id,
                    amount, value, value + amount);
            db.put(id, value + amount);
        } else {
            System.out.printf("UPDATE: Error! Database doesn't contain the id: %d\n", id);
            fail = true;
        }
        return fail;
    }

    public boolean alter(int id, boolean add) {
        boolean fail = false;
        if (add) {
            if (!db.containsKey(id)) {
                System.out.printf("ALTER (ADD): Added row %d to database\n", id);
                db.put(id, 0);
            } else {
                System.out.printf("ALTER (ADD): Error! Database already contains the id: %d\n", id);
                fail = true;
            }
        } else {
            if (db.containsKey(id)) {
                System.out.printf("ALTER (REMOVE): Removed  row %d to database\n", id);
                db.remove(id);
            } else {
                System.out.printf("ALTER (REMOVE): Error! Database doesn't contain the id: %d\n", id);
                fail = true;
            }
        }
        return fail;
    }

    public boolean select(int id) {
        boolean fail = false;
        if (db.containsKey(id)) {
            int value = db.get(id);
            System.out.printf("SELECT: Selected row %d from database: %d", id, value);
        } else {
            System.out.printf("SELECT: Error! Database doesn't contain the id: %d\n", id);
            fail = true;
        }
        return fail;
    }

}