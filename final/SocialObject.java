import java.util.Random;
import java.awt.Graphics2D;

public class SocialObject {
    private SocialState healthyState;
    private SocialState standbyState; // on collision
    private SocialState infectedState;
    private SocialState inHospitalState;
    private SocialState state;

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

    private int standby = -501;
    private Random random = new Random();

    private float lifetime = 0;
    private Mediator mediator;

    public SocialObject(Mediator mediator, int width, int height) {
        healthyState = new HealthyState(this);
        standbyState = new StandbyState(this);
        infectedState = new InfectedState(this);
        inHospitalState = new InHospitalState(this);
        state = healthyState;

        S = random.nextInt(maxS) + 1;
        M = random.nextFloat();
        D = random.nextInt(maxD + 1);
        C = random.nextInt(maxC) + 1;

        // random starting point and directions initally.
        x = (int) (Math.random() * width);
        y = (int) (Math.random() * height);

        dx = delta();
        dy = delta();

        this.mediator = mediator;
    }

    private int delta() {
        return Math.random() > 0.5 ? 1 : -1;
    }

    public void update(int delta) {
        state.update(delta);
    }

    public void checkCollision(SocialObject other) {
        if (state.checkCollision(other))
            mediator.notify(this, other, "collision");
    }

    public void paint(Graphics2D g2d) {
        state.paint(g2d);
    }

    public void setState(SocialState state) {
        this.state = state;
    }

    public SocialState getState() {
        return this.state;
    }

    private float infectingProb(int R, SocialObject other) {
        int dist = Math.min(D, other.D);
        int time = Math.max(C, other.C);

        return R * (1 + (float) (time) / 10F) * M * other.M * (1 - (float) (dist) / 10F);
    }

    public SocialState getHealthyState() {
        return this.healthyState;
    }

    public SocialState getInfectedState() {
        return this.infectedState;
    }

    public SocialState getStandbyState() {
        return this.standbyState;
    }


    public SocialState getInHospitalState() {
        return this.inHospitalState;
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

    public boolean isInfected() {
        return getState() instanceof InfectedState;
    }

    public boolean inCanvas() {
        return !(getState() instanceof InHospitalState);
    }

    public boolean isJustCollided() {
        return getStandby() >= -500;
    }

}