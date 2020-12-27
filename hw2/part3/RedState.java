public class RedState extends AbstractState {

    public RedState(TrafficLight trafficLight) {
        super(trafficLight);

    }

    @Override
    public void toGreen() {
        switchTo(trafficLight.getGreenState(), 15);
    }

}
