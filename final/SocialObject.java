import java.awt.Color;
import java.awt.Graphics;

public class SocialObject {
    // constant speed of S pixels/second.
    private double S = 1;

    // social interaction time with another indivicual
    private double C;

    private static final int size = 5;

    private double x;
    private double y;

    // direction of indivicual which will be assigned randomly
    private double dx;
    private double dy;

    public SocialObject(int width, int height) {
        // random starting point and directions initally.
        x = Math.random() * width;
        y = Math.random() * height;

        dx = delta();
        dy = delta();

    }

    public void update(int xlower, int xupper, int ylower, int yupper) {

        // TODO. refactor this.
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
    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect((int) x, (int) y, size, size);
    }

    private double delta() {
        return Math.random() > 0.5 ? 1 : -1;

    }

}