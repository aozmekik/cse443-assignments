public class YellowState extends AbstractState {

    public YellowState(TrafficLight trafficLight) {
        super(trafficLight);
    }

    @Override
    public void toRed() {
        switchTo(trafficLight.getRedState(), 3);
    }

}
