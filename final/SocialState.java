
/**
 * State Design Pattern implementation.
 * @see SocialObject
 * 
 * Has three functions changing when the state changes.
 */

import java.awt.Graphics2D;

public interface SocialState {
    /**
     * Updates the social object coordinates on every delta ms.
     * 
     * @param delta, refresh rate.
     */
    public void update(int delta);

    /**
     * Checks if this object collides with other object.
     * 
     * @param delta, refresh rate.
     */
    public boolean checkCollision(SocialObject other);

    /**
     * Paints the object to the graphical screen.
     */
    public void paint(Graphics2D g2d);
}
