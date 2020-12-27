public class TrafficLight {
    private State redState;
    private State yellowState;
    private State greenState;

    private State state;

    public TrafficLight() {
        redState = new RedState(this);
        yellowState = new YellowState(this);
        greenState = new GreenState(this);

        state = redState;
    }

    public void setState(State state) {
        System.out.println("Old State: " + this.state);
        this.state = state;
        System.out.println("New State: " + this.state);

    }

    public void toGreen() {
        state.toGreen();
    }

    public void toRed() {
        state.toRed();
    }

    public void toYellow() {
        state.toYellow();
    }

    public State getState() {
        return state;
    }

    public State getRedState() {
        return redState;
    }

    public State getYellowState() {
        return yellowState;
    }

    public State getGreenState() {
        return greenState;
    }
}
