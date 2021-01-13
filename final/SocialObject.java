import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


// TODO. die of infected
// TODO. print info on screen
// TODO. hospitality

public class SocialObject {
    public enum HealthState {
        HEALTHY, SICK, WILL_BE_SICK
    }

    private HealthState healthState;
    private Color color;

    private int S; // constant speed of S pixels/second.
    private int C; // social interaction time with another individual
    private int D; // social distance causing social interaction
    private float M;

    private static final float R = 0.5F + (new Random().nextFloat() * 0.5F); // disease spreading factor [0.5, 1.0]
    private static final float Z = 0.1F + (new Random().nextFloat() * 0.8F); // disease mortality rate [0.1, 0.9]

    private static final int maxS = 5; // [0, 5]
    // private static final int maxM = 1; // [0, 1]
    private static final int maxD = 9; // [0, 9]
    private static final int maxC = 5; // [0, 5]

    private static final int size = 5;
    private static final int fps = 30;

    private int x;
    private int y;

    // direction of indivicual which will be assigned randomly
    private int dx;
    private int dy;

    private int standby = 0;
    private Random random = new Random();

    public SocialObject(int width, int height) {
        healthState = HealthState.HEALTHY;
        setColor();

        S = random.nextInt(maxS) + 1;
        M = random.nextFloat();
        D = random.nextInt(maxD + 1);
        C = random.nextInt(maxC) + 1;

        // random starting point and directions initally.
        x = (int) (Math.random() * width);
        y = (int) (Math.random() * height);

        dx = delta();
        dy = delta();

    }

    public void update(int xlower, int xupper, int ylower, int yupper) {

        standby -= fps;
        if (!onStandby()) { // there is no time debt to pay with waiting
            if (healthState == HealthState.WILL_BE_SICK)
                healthState = HealthState.SICK;

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

        setColor();
    }

    public void paint(Graphics g) {
        g.setColor(getColor());
        g.fillRect(x, y, size, size);
    }

    private int delta() {
        return Math.random() > 0.5 ? 1 : -1;
    }

    public void checkCollision(SocialObject other) {
        if (!onStandby() && !other.onStandby()) {

            // if (isInfected())
            // System.out.println("Infected");

            // FIXME. I added size here
            int dist = Math.min(getD(), other.getD()) + size;
            Rectangle r1 = new Rectangle(getX(), getY(), dist, dist);
            Rectangle r2 = new Rectangle(other.getX(), other.getY(), dist, dist);

            if (r1.intersects(r2)) {
                // in miliseconds.
                int time = (Math.max(getC(), other.getC())) * 1000;

                setStandby(time);
                other.setStandby(time);
                dx = dx == other.dx ? -dx: dx;
                dy = dy == other.dy ? -dy: dy;


                if (isInfected()) {
                    // float P = infectingProb(other);
                    // System.out.println(P);
                    // if (infectingProb(other) > 0.5F)
                        other.setHealthState(HealthState.WILL_BE_SICK);

                } else if (other.isInfected()) {
                    // float P = infectingProb(other);
                    // System.out.println(P);
                    // if (other.infectingProb(this) > 0.5F)
                    setHealthState(HealthState.WILL_BE_SICK);
                }
            }
        }

    }

    private boolean isInfected() {
        return healthState == HealthState.SICK;
    }

    private void setColor() {
        switch (healthState) {
            case HEALTHY:
                color = Color.WHITE;
                break;
            case SICK:
                color = Color.RED;
                break;
            case WILL_BE_SICK:
                color = Color.YELLOW;
                break;
            default:
                color = Color.WHITE;
        }
    }

    private float infectingProb(SocialObject other) {
        int dist = Math.min(D, other.D);
        int time = Math.max(C, other.C);

        return R * (1 + (float) (time) / 10F) * M * other.M * (1 - (float) (dist) / 10F);
    }

    public HealthState getHealthState() {
        return this.healthState;
    }

    public void setHealthState(HealthState healthState) {
        this.healthState = healthState;
    }

    public Color getColor() {
        return this.color;
    }

    public int getS() {
        return this.S;
    }

    public void setS(int S) {
        this.S = S;
    }

    public int getC() {
        return this.C;
    }

    public void setC(int C) {
        this.C = C;
    }

    public int getD() {
        return this.D;
    }

    public void setD(int D) {
        this.D = D;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDx() {
        return this.dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return this.dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getStandby() {
        return this.standby;
    }

    public boolean onStandby() {
        return this.standby >= 0;
    }

    public void setStandby(int standby) {
        this.standby = standby;
    }

}