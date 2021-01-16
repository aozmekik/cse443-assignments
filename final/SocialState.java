import java.awt.Graphics2D;

public interface SocialState {
    public void update(int delta);

    public boolean checkCollision(SocialObject other);

    public void paint(Graphics2D g2d);
}
