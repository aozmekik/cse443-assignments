public class GreenState extends AbstractState {
    private int timeout_X = 60;

    public GreenState(TrafficLight trafficLight) {
        super(trafficLight);
    }

    @Override
    public void toYellow() {
        switchTo(trafficLight.getYellowState(), timeout_X);
    }
    

}
