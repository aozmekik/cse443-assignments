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
