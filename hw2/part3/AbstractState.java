public class AbstractState implements State {
    protected TrafficLight trafficLight;
    private int timeout;

    public AbstractState(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
    }

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
