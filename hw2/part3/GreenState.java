/**
 * Concrete state GreenState.
 * 
 * Has only transition to YellowState, hence only implements toYellow method.
 * Default timeout for transition is set to 60 sec.
 */

public class GreenState extends AbstractState {

    public GreenState(TrafficLight trafficLight) {
        super(trafficLight);
        setTimeout(60);
    }

    @Override
    public void toYellow() {
        switchTo(trafficLight.getYellowState(), getTimeout());
    }

}
