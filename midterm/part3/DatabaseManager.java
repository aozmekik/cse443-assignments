import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * DatabaseManager. Manages the Database. In case of fail rolls back the
 * database to a previous state.
 * 
 */

public class DatabaseManager {
    private List<Operation> operationList = new ArrayList<Operation>();

    public void takeOperation(Operation Operation) {
        operationList.add(Operation);
    }

    public void performOperations() {
        Stack<Operation> operationStack = new Stack<Operation>();
        boolean fail = true;

        for (Operation operation : operationList) {
            fail = operation.operate();
            if (fail)
                break;
            else
                operationStack.push(operation);
        }

        if (fail) {
            System.out.printf("Rolling back to a previous state in database...\n");
            while (!operationStack.empty())
                operationStack.pop().reverse();
        }

        operationList.clear();
    }
}