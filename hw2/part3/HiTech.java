import java.util.ArrayList;
import java.util.List;

public class HiTech {
    private List<Observer> observers = new ArrayList<Observer>();
    private int normalTimeout = 60;
    private int increasedTimeout = 90;

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void changeDetected(boolean flag) {
        int updateTimeout = flag ? increasedTimeout : normalTimeout;
        for (Observer observer : observers) {
            observer.update(updateTimeout);
        }

    }
}