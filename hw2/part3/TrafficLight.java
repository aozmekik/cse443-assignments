public class TrafficLight extends Observer {
    private State redState;
    private State yellowState;
    private State greenState;

    private State state;

    public TrafficLight(HiTech subject) {
        redState = new RedState(this);
        yellowState = new YellowState(this);
        greenState = new GreenState(this);

        state = redState;
        
        this.subject = subject;
        this.subject.attach(this);
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

    public void reset() {
        state = redState;
    }

    @Override
    public void update(int timeout) {
        greenState.setTimeout(timeout);
    }
}
