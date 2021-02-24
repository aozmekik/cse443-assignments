
/**
 * Mediator design pattern implementation.
 * 
 * Objects notify mediator when the collide with an another object.
 * Mediator object handles the collision.
 * 
 * @see SocialObject
 * @see SocietyController 
 */

interface Mediator {
    public void notify(SocialObject so, SocialObject other, String event);
}
