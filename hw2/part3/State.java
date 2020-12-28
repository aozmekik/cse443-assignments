/**
 * Basic implementation of State Design Pattern.
 * 
 * Represents the State interface. We have 4 states and toX methods represents
 * the transitions. setTimeout method represents the timeout of the transition
 * for that State.
 */

public interface State {

    /**
     * Switches this state to Green state.
     */
    public void toGreen();

    /**
     * Switches this state to Red state.
     */
    public void toRed();

    /**
     * Switches this state to Yellow state.
     */
    public void toYellow();

    /**
     * Sets the timeout for transitions of this state.
     */
    public void setTimeout(int timeout);
}