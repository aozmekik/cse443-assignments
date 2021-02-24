/**
 * Observer Design Pattern implementation
 * 
 * Plotter objects must get some coordinates of which is the dead and infected
 * count. Since might be more than one plot, and plotters should observer a
 * value change in society controller, they apply observer design pattern.
 * 
 * @see Plotter
 * @see SocietyController
 */

public abstract class Observer {
    protected SocietyController subject;

    public Observer(SocietyController subject) {
        this.subject = subject;
    }

    public abstract void update();
}
