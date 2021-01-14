import java.awt.Graphics2D;
import java.awt.Color;

public class InHospitalState extends HealthyState {

    public InHospitalState(SocialObject socialObject) {
        super(socialObject);
    }

    @Override
    public void update(int delta) {
        so.increaseLifetime(delta);
        if (so.getLifetime() >= sc.getHospitalStandby()) {
            so.setState(so.getHealthyState());
            sc.updateLabel("healthy", 1);
            sc.updateLabel("hospitalized", -1);
            sc.updateLog("An individual discharged from hospital.");
            setColor(Color.WHITE);
        }
    }

    @Override
    public void checkCollision(SocialObject other) {
        // intentionally left blank
    }

    @Override
    public void paint(Graphics2D g2d) {
        // intentionally left blank
    }

}
