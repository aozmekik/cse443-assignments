import java.awt.Color;
import java.awt.Rectangle;

public class InfectedState extends HealthyState {

    public InfectedState(SocialObject socialObject) {
        super(socialObject);
        color = Color.RED;
    }

    @Override
    public void update(int delta) {
        super.update(delta);
        if (so.getLifetime() >= sc.getHospitalArrival() && !sc.hospitalFull()) {
            so.setState(so.getInHospitalState());
            so.setLifetime(0);
            sc.updateLabel("hospitalized", 1);
            sc.updateLabel("infected", -1);
            sc.updateLog("An individual went to hospital.");
        }

        else if (so.getLifetime() >= sc.getInfectedLifetime()) {
            so.setState(so.getDeadState());
            sc.updateLabel("dead", 1);
            sc.updateLabel("infected", -1);
            sc.updateLog("An individual died.");
        }
    }

    @Override
    public void checkCollision(SocialObject other) {
        if (collides(other)) {
            int time = (Math.max(so.getC(), other.getC())) * 1000; // in miliseconds.

            StandbyState standbyState;
            standbyState = (StandbyState) so.getStandbyState();
            standbyState.setMoveOnState(standbyState.getInfectedMoveOnState());

            standbyState = (StandbyState) other.getStandbyState();
            if (other.isInfected())
                standbyState.setMoveOnState(standbyState.getInfectedMoveOnState());
            else
                standbyState.setMoveOnState(standbyState.getWillbeInfectedMoveOnState());

            so.setStandby(time);
            other.setStandby(time);
            so.setState(so.getStandbyState());
            other.setState(other.getStandbyState());

            int dx = so.getDx();
            int dy = so.getDy();
            so.setDx(dx == other.getDx() ? -dx : dx);
            so.setDy(dy == other.getDy() ? -dy : dy);
        }

        // SocialState state = other.getState();
        // boolean collided = super.checkCollision(other);
        // if (collided && !(state instanceof InfectedState)) {
        // StandbyState standbyState;
        // standbyState = (StandbyState) so.getState();
        // standbyState.setState(standbyState.getInfectedState());

        // standbyState = (StandbyState) other.getState();
        // standbyState.setState(standbyState.getWillbeInfectedState());
        // }
        // return collided;
    }
}
