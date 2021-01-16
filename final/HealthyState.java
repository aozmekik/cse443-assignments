import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Color;

public class HealthyState implements SocialState {
    protected SocialObject so;
    protected Color color;
    // FIXME delete sc. or what?

    protected static int objectSize = 0, width = 0, height = 0;
    protected static int xlower = 0, xupper = 0, ylower = 0, yupper = 0;

    protected static SocietyController sc;

    public HealthyState(SocialObject socialObject) {
        so = socialObject;
        color = Color.WHITE;
    }

    @Override
    public void update(int delta) {
        so.increaseLifetime(delta);
        so.setStandby(so.getStandby() - delta);

        int x = so.getX();
        int y = so.getY();
        int dx = so.getDx();
        int dy = so.getDy();
        int S = so.getS();

        x += dx * S;
        if (x < xlower || x > xupper) {
            x -= dx * S;
            dx = -dx;
            x += dx * S;
        }

        y += dy * S;
        if (y < ylower || y > yupper) {
            y -= dy * S;
            dy = -dy;
            y += dy * S;
        }

        so.setX(x);
        so.setY(y);
        so.setDx(dx);
        so.setDy(dy);
    }

    @Override
    public boolean checkCollision(SocialObject other) {
        return collides(other);
    }

    @Override
    public void paint(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillRect(so.getX(), so.getY(), objectSize, objectSize);
    }

    public static void setSettings(int size, int w, int h, SocietyController sf) {
        objectSize = size;
        width = w;
        height = h;
        xlower = 0;
        xupper = width - objectSize;
        ylower = 0;
        yupper = height - objectSize;
        sc = sf;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean collides(SocialObject other) {
        boolean collision = false;
        if (so.inCanvas() && other.inCanvas() && !so.isJustCollided() && !other.isJustCollided()) {
            int dist = Math.min(so.getD(), other.getD()) + objectSize;
            Rectangle r1 = new Rectangle(so.getX(), so.getY(), dist, dist);
            Rectangle r2 = new Rectangle(other.getX(), other.getY(), dist, dist);

            collision = r1.intersects(r2);
        }
        return collision;
    }
}
