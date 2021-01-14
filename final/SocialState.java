import java.awt.Graphics2D;

public interface SocialState {
    public void update(int delta);

    public void checkCollision(SocialObject other);

    public void paint(Graphics2D g2d);
}
