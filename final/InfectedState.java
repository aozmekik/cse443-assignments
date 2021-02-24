
/**
 * Infected, part of State Design Pattern implementation.
 * @see SocialObject
 * @see SocialState
 * 
 */

import java.awt.Color;

public class InfectedState extends HealthyState {

    public InfectedState(SocialObject socialObject) {
        super(socialObject);
        color = Color.RED;
    }

    /**
     * Updates the infected object's coordinates. Difference is, one infected
     * must visit the hospital in an interval, also it may die in that interval.
     */
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
            sc.toDead(so);
            sc.updateLabel("dead", 1);
            sc.updateLabel("infected", -1);
            sc.updateLog("An individual died.");
        }
    }

}
