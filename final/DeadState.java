import java.awt.Graphics2D;

public class DeadState extends HealthyState {

    public DeadState(SocialObject socialObject) {
        super(socialObject);
    }

    @Override
    public void update(int delta) {
        // intentionally left blank
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
