/**
 * Some of the methods in the state interface should not be active, as there is
 * no direct transition between some states. This class was written to reuse
 * with an abstarct state class, to avoid making these all the same one at a
 * time in each state.
 * 
 * In default all transitions fail. Child state must reimplement the appropriate
 * transition methods.
 * 
 */

public class AbstractState implements State {
    protected TrafficLight trafficLight;
    private int timeout;

    public AbstractState(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
    }

    /**
     * Simulates the switching from state to states.
     */
    protected void switchTo(State newState, int sec) {
        System.out.printf("Switching from %s to %s in %d sec\n", trafficLight.getState(), newState, sec);
        trafficLight.setState(newState);
    }

    @Override
    public void toGreen() {
        error("switching to GreenState");
    }

    @Override
    public void toRed() {
        error("switching to RedState");

    }

    @Override
    public void toYellow() {
        error("switching to YellowState");

    }

    private void error(String action) {
        System.out.println("Unsupported operation for " + toString() + ": " + action);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getTimeout() {
        return timeout;
    }

}
