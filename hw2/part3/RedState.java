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
