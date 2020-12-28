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
