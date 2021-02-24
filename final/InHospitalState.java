
/**
 * InHospitalState, represents the social object being in hospital state.
 * A part of State Design Pattern implementation.
 * @see SocialObject
 * @see SocialState
 * 
 */

import java.awt.Graphics2D;
import java.awt.Color;

public class InHospitalState extends HealthyState {

    public InHospitalState(SocialObject socialObject) {
        super(socialObject);
    }

    /**
     * Infected social object must be discharged from hospital in an determined
     * amount of time. No updating of coordinates whatsoever.
     */
    @Override
    public void update(int delta) {
        so.increaseLifetime(delta);
        if (so.getLifetime() >= sc.getHospitalStandby()) {
            so.setState(so.getHealthyState());
            sc.updateLabel("healthy", 1);
            sc.updateLabel("hospitalized", -1);
            sc.updateLog("An individual discharged from hospital.");
            setColor(Color.WHITE);
            StandbyState standbyState = ((StandbyState) so.getStandbyState());
            standbyState.setMoveOnState(standbyState.getHealthyMoveOnState());
        }
    }

    /**
     * No collision of social object in the hospital.
     */
    @Override
    public boolean checkCollision(SocialObject other) {
        // intentionally left blank
        return false;
    }

    /**
     * No painting of social object in the hospital.
     */
    @Override
    public void paint(Graphics2D g2d) {
        // intentionally left blank
    }

}
