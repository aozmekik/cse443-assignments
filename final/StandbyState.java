import java.awt.Color;


public class StandbyState extends HealthyState {
    private MoveOnState healthyMoveOn = new HealthyMoveOnState(this);
    private MoveOnState willBeInfectedMoveOnState = new WillBeInfectedMoveOnState(this);
    private MoveOnState infectedMoveOnState = new InfectedMoveOnState(this);
    private MoveOnState moveOnState;

    public StandbyState(SocialObject socialObject) {
        super(socialObject);
        moveOnState = healthyMoveOn;
    }

    @Override
    public void update(int delta) {
        so.increaseLifetime(delta);
        so.setStandby(so.getStandby() - delta);
        // satisfied the waiting period
        if (so.getStandby() <= 0)
            moveOnState.moveOn();
    }

    @Override
    public void checkCollision(SocialObject other) {
        // intentionally left blank.
    }

    public void setMoveOnState(MoveOnState handler) {
        this.moveOnState = handler;
        color = handler.getColor();
    }

    public MoveOnState getHealthyMoveOnState() {
        return this.healthyMoveOn;
    }

    public MoveOnState getWillbeInfectedMoveOnState() {
        return this.willBeInfectedMoveOnState;
    }

    public MoveOnState getInfectedMoveOnState() {
        return this.infectedMoveOnState;
    }

    public MoveOnState getMoveOnState() {
        return this.moveOnState;
    }

    public abstract class MoveOnState {
        protected StandbyState standByState;

        public MoveOnState(StandbyState state)
        {
            this.standByState = state;
        }

        public abstract void moveOn();
        public abstract Color getColor();
    }

    public class HealthyMoveOnState extends MoveOnState {
        public HealthyMoveOnState(StandbyState state) {
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

    public class WillBeInfectedMoveOnState extends MoveOnState {

        public WillBeInfectedMoveOnState(StandbyState state) {
            super(state);
        }

        @Override
        public void moveOn() {
            so.setState(so.getInfectedState());
            so.setLifetime(0);
            sc.updateLabel("healthy", -1);
            sc.updateLabel("infected", 1);
            sc.updateLog("An individual got infected.");
            standByState.setMoveOnState(standByState.getInfectedMoveOnState());
        }

        @Override
        public Color getColor() {
            return Color.YELLOW;
        }

    }

    public class InfectedMoveOnState extends MoveOnState {

        public InfectedMoveOnState(StandbyState state) {
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
