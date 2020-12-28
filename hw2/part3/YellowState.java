/**
 * Concrete state YellowState.
 * 
 * Has only transition to RedState, hence only implements toRed method.
 * Default timeout for transition is set to 3 sec. Timeout is absolute in the
 * design and will not be changed.
 */

public class YellowState extends AbstractState {

    public YellowState(TrafficLight trafficLight) {
        super(trafficLight);
        setTimeout(3);
    }

    @Override
    public void toRed() {
        switchTo(trafficLight.getRedState(), getTimeout());
    }

}
