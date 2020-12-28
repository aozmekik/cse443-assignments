/**
 * Concrete state RedState.
 * 
 * Has only transition to GreenState, hence only implements toGreen method.
 * Default timeout for transition is set to 15 sec. Timeout is absolute in the
 * design and will not be changed.
 */

public class RedState extends AbstractState {

    public RedState(TrafficLight trafficLight) {
        super(trafficLight);
        setTimeout(15);

    }

    @Override
    public void toGreen() {
        switchTo(trafficLight.getGreenState(), getTimeout());
    }

}
