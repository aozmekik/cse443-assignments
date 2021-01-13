import java.awt.Color;
import java.util.Random;


public class SocialObject {
    public enum HealthState {
        HEALTHY, INFECTED, WILL_BE_INFECTED, DEAD, IN_HOSPITAL
    }

    private HealthState healthState;
    private Color color;

    private int S; // constant speed of S pixels/second.
    private int C; // social interaction time with another individual
    private int D; // social distance causing social interaction
    private float M;

    private static final int maxS = 5; // [0, 5]
    // private static final int maxM = 1; // [0, 1]
    private static final int maxD = 9; // [0, 9]
    private static final int maxC = 5; // [0, 5]

    private int x;
    private int y;

    // direction of indivicual which will be assigned randomly
    private int dx;
    private int dy;

    private int standby = 0;
    private Random random = new Random();

    private float lifetime = 1;

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

    private int delta() {
        return Math.random() > 0.5 ? 1 : -1;
    }

    public boolean isInfected() {
        return healthState == HealthState.INFECTED;
    }

    public void setColor() {
        switch (healthState) {
            case HEALTHY:
                color = Color.WHITE;
                break;
            case INFECTED:
                color = Color.RED;
                break;
            case WILL_BE_INFECTED:
                color = Color.YELLOW;
                break;
            default:
                color = Color.WHITE;
        }
    }

    private float infectingProb(int R, SocialObject other) {
        int dist = Math.min(D, other.D);
        int time = Math.max(C, other.C);

        return R * (1 + (float) (time) / 10F) * M * other.M * (1 - (float) (dist) / 10F);
    }

    public HealthState getHealthState() {
        return this.healthState;
    }

    public void setHealthState(HealthState healthState) {
        this.healthState = healthState;
        setColor();
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
        return this.standby > 0;
    }

    public void setStandby(int standby) {
        this.standby = standby;
    }

    public float getLifetime() {
        return this.lifetime;
    }

    public void setLifetime(float lifetime) {
        this.lifetime = lifetime;
    }

    public void increaseLifetime(int x) {
        this.lifetime += x;
    }

    public boolean inCanvas() {
        return healthState != HealthState.DEAD && healthState != HealthState.IN_HOSPITAL;
    }

    public boolean inHospital() {
        return healthState == HealthState.IN_HOSPITAL;
    }

}