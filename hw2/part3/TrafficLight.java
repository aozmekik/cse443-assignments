/**
 * TrafficLigth manages the States while applying a compound pattern by
 * implementing both Observer Design Pattern and State Design Pattern.
 * 
 */

public class TrafficLight extends Observer {
    private State redState;
    private State yellowState;
    private State greenState;

    private State state;

    /**
     * Creates the TrafficLight with the given HiTech subject. TrafficLight is
     * context for the states and also an observer object.
     * 
     * @param subject HiTech.
     */
    public TrafficLight(HiTech subject) {
        redState = new RedState(this);
        yellowState = new YellowState(this);
        greenState = new GreenState(this);

        state = redState;

        this.subject = subject;
        this.subject.attach(this);
    }

    /**
     * Sets the state of TrafficLight.
     */
    public void setState(State state) {
        System.out.println("Old State: " + this.state);
        this.state = state;
        System.out.println("New State: " + this.state);

    }

    /**
     * Switches to green after 15 seconds.
     */
    public void toGreen() {
        state.toGreen();
    }

    /**
     * Switches to red after 3 seconds.
     */
    public void toRed() {
        state.toRed();
    }

    /**
     * Switches to yellow after 60 seconds.
     */
    public void toYellow() {
        state.toYellow();
    }

    /**
     * Gets the current state of context.
     */
    public State getState() {
        return state;
    }

    /**
     * Gets the red state of context.
     */
    public State getRedState() {
        return redState;
    }

    /**
     * Gets the yellow state of context.
     */
    public State getYellowState() {
        return yellowState;
    }

    /**
     * Gets the green state of context.
     */
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
