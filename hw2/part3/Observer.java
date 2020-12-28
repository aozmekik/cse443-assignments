/**
 * Observer interface for basic implementation of Observer Design Pattern.
 * 
 * update method gets the new timeout value and updates the timeout.
 */

public abstract class Observer {
    protected HiTech subject;

    public abstract void update(int timeout);
}