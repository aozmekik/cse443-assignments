import java.awt.Color;


public class StandbyState extends HealthyState {
    private MoveOnState healthyState = new HealthyState(this);
    private MoveOnState wilBeInfectedState = new WillBeInfectedState(this);
    private MoveOnState infectedState = new InfectedState(this);
    private MoveOnState state;

    public StandbyState(SocialObject socialObject) {
        super(socialObject);
        state = healthyState;
    }

    @Override
    public void update(int delta) {
        so.increaseLifetime(delta);
        so.setStandby(so.getStandby() - delta);
        // satisfied the waiting period
        if (so.getStandby() <= 0)
            state.moveOn();
    }

    @Override
    public void checkCollision(SocialObject other) {
        // intentionally left blank.
    }

    public void setState(MoveOnState handler) {
        this.state = handler;
        color = handler.getColor();
    }

    public MoveOnState getHealthyState() {
        return this.healthyState;
    }

    public MoveOnState getWillbeInfectedState() {
        return this.wilBeInfectedState;
    }

    public MoveOnState getInfectedState() {
        return this.infectedState;
    }

    public MoveOnState getState() {
        return this.state;
    }

    public abstract class MoveOnState {
        protected StandbyState state;

        public MoveOnState(StandbyState state)
        {
            this.state = state;
        }

        public abstract void moveOn();
        public abstract Color getColor();
    }

    public class HealthyState extends MoveOnState {
        public HealthyState(StandbyState state) {
            super(state);
        }

        @Override
        public void moveOn() {
            so.setState(so.getHealthyState());
        }

        @Override
        public Color getColor() {
            return Color.WHITE;
        }
    }

    public class WillBeInfectedState extends MoveOnState {

        public WillBeInfectedState(StandbyState state) {
            super(state);
        }

        @Override
        public void moveOn() {
            so.setState(so.getInfectedState());
            so.setLifetime(0);
            sc.updateLabel("healthy", -1);
            sc.updateLabel("infected", 1);
            sc.updateLog("An individual got infected.");
            state.setState(state.getInfectedState());
        }

        @Override
        public Color getColor() {
            return Color.YELLOW;
        }

    }

    public class InfectedState extends MoveOnState {

        public InfectedState(StandbyState state) {
            super(state);
        }

        @Override
        public void moveOn() {
            so.setState(so.getInfectedState());
        }

        @Override
        public Color getColor() {
            return Color.RED;
        }

    }

}
