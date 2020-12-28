
/**
 * Part of Observer Design Pattern.
 * HiTech represents the subject in observer pattern.
 * 
 * If changeDetected method is invoked, it updates all of the
 * observers with the apropriate timeout.
 */

import java.util.ArrayList;
import java.util.List;

public class HiTech {
    private List<Observer> observers = new ArrayList<Observer>();
    private int normalTimeout = 60;
    private int increasedTimeout = 90;

    public void attach(Observer observer) {
        observers.add(observer);
    }

    /**
     * It simulates the situation of whenever the camera detects a change of traffic
     * the method changeDetected of the class HiTech (provided by the software
     * library of the camera) is called automatically by the hardware:
     * 
     * @param flag if flag is true, it means the traffic has increased
     *             substantially, otherwise (if false), everything is normal, so
     *             timeout_X returns to its initial value.
     */
    public void changeDetected(boolean flag) {
        int updateTimeout = flag ? increasedTimeout : normalTimeout;
        for (Observer observer : observers) {
            observer.update(updateTimeout);
        }

    }
}