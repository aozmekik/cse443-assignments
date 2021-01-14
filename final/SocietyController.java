import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;

// TODO. start satisfying the goals.
// TODO. last thing is add gui elements for modify.I

// TODO. design pattern for individuals.
// TODO. state design pattern.

public class SocietyController implements KeyListener {
    private final int objectSize = 5;
    private final int P = 100;
    private final int B = P / 100; // FIXME. B = P / 100
    private final float R = 0.5F + (new Random().nextFloat() * 0.5F); // disease spreading factor [0.5, 1.0]
    private final float Z = 0.1F + (new Random().nextFloat() * 0.8F); // disease mortality rate [0.1, 0.9]
    private final int hospitalArrival = 25000; // 25 sec.
    private final int hospitalStandby = 10000; // 10 sec

    private SocialObject[] socialObjects = new SocialObject[P];

    private Random random = new Random();

    private static final int delta = 30;

    private Map<String, LabelObserver> labelObservers = new HashMap<String, LabelObserver>();
    private Map<String, Integer> values = new HashMap<String, Integer>();

    private boolean pause = false;
    private float infectedLifetime;
    // private int logIndex = -1;
    private String logs[] = new String[5];

    public SocietyController(int width, int height) {

        for (int i = 0; i < socialObjects.length; i++)
            socialObjects[i] = new SocialObject(width, height);

        values.put("infected", 1);
        values.put("healthy", P - 1);
        values.put("hospitalized", 0);
        values.put("dead", 0);
        values.put("time", 0);

        HealthyState.setSettings(objectSize, width, height, this);

        for (int i = 0; i < logs.length; ++i)
            logs[i] = " ";

        infectedLifetime = (100 * (1 - Z)) * 1000;

        // for (int i = 0; i < socialObjects.length; i++)
        //     socialObjects[i].setState(socialObjects[i].getInfectedState());

        // random infected among the population
        SocialObject so = socialObjects[random.nextInt(socialObjects.length)];
        so.setState(so.getInfectedState());
    }

    public void update() {
        if (!pause) {
            updateTime();
            // for (SocialObject socialObject : socialObjects)
            // socialObject.update(delta);
            for (int i = 0; i < socialObjects.length; i++) {
                socialObjects[i].update(delta);
                // if (socialObjects[i].getState() instanceof InfectedState)
                //     System.out.printf("[%d]: %f\n",i,  socialObjects[i].getLifetime());
            }

            // if (socialObject.getHealthState() != SocialObject.HealthState.DEAD)
            // move(socialObject);

            for (int i = 0; i < socialObjects.length; i++) {
                for (int j = i + 1; j < socialObjects.length; j++)
                    socialObjects[i].checkCollision(socialObjects[j]);
            }

            // for (int i = 0; i < socialObjects.length; i++) {
            // for (int j = i + 1; j < socialObjects.length; j++) {
            // if (socialObjects[i].inCanvas() && socialObjects[j].inCanvas())
            // checkCollision(socialObjects[i], socialObjects[j]);
            // }
            // }
        }

    }

    // public void move(SocialObject a) {

    // a.increaseLifetime(delta);
    // if (a.isInfected() || a.inHospital()) {

    // if (a.inHospital() && a.getLifetime() >= hospitalStaying) {
    // a.setHealthState(SocialObject.HealthState.HEALTHY);
    // updateLabel("healthy", 1);
    // updateLabel("hospitalized", -1, String.format("/%d", B));
    // inHospital--;
    // updateLog("An individual discharged from hospital.");
    // } else if (!a.onStandby()) {
    // if (a.getLifetime() >= hospitalArrival && !hospitalFull()) {
    // a.setHealthState(SocialObject.HealthState.IN_HOSPITAL);
    // a.setLifetime(0);
    // updateLabel("hospitalized", 1, String.format("/%d", B));
    // updateLabel("infected", -1);
    // inHospital++;
    // updateLog("An individual went to hospital.");
    // }

    // else if (a.getLifetime() >= infectedLifetime) {
    // a.setHealthState(SocialObject.HealthState.DEAD);
    // updateLabel("dead", 1);
    // updateLabel("infected", -1);
    // updateLog("An individual died.");
    // }
    // }
    // }

    // a.setStandby(a.getStandby() - delta);
    // if (!a.onStandby() && !a.inHospital()) {
    // if (a.getHealthState() == SocialObject.HealthState.WILL_BE_INFECTED) {
    // a.setHealthState(SocialObject.HealthState.INFECTED);
    // a.setLifetime(0);
    // updateLabel("healthy", -1);
    // updateLabel("infected", 1);
    // updateLog("An individual got infected.");
    // }

    // int x = a.getX();
    // int y = a.getY();
    // int dx = a.getDx();
    // int dy = a.getDy();
    // int S = a.getS();

    // x += dx * S;
    // if (x < xlower || x > xupper) {
    // x -= dx * S;
    // dx = -dx;
    // x += dx * S;
    // }

    // y += dy * S;
    // if (y < ylower || y > yupper) {
    // y -= dy * S;
    // dy = -dy;
    // y += dy * S;
    // }

    // a.setX(x);
    // a.setY(y);
    // a.setDx(dx);
    // a.setDy(dy);
    // }

    // }

    // public void checkCollision(SocialObject a, SocialObject b) {
    // if (!isJustCollided(a) && !isJustCollided(b)) {

    // int dist = Math.min(a.getD(), b.getD()) + objectSize;
    // Rectangle r1 = new Rectangle(a.getX(), a.getY(), dist, dist);
    // Rectangle r2 = new Rectangle(b.getX(), b.getY(), dist, dist);

    // if (r1.intersects(r2)) {
    // // in miliseconds.
    // int time = (Math.max(a.getC(), b.getC())) * 1000;

    // a.setStandby(time);
    // b.setStandby(time);

    // int dx = a.getDx();
    // int dy = a.getDy();
    // a.setDx(dx == b.getDx() ? -dx : dx);
    // a.setDy(dy == b.getDy() ? -dy : dy);

    // if (a.isInfected() && !b.isInfected()) {
    // // float P = infectingProb(other);
    // // System.out.println(P);
    // // if (infectingProb(other) > 0.5F)
    // b.setHealthState(SocialObject.HealthState.WILL_BE_INFECTED);

    // } else if (b.isInfected() && !a.isInfected()) {
    // // float P = infectingProb(other);
    // // System.out.println(P);
    // // if (other.infectingProb(this) > 0.5F)
    // a.setHealthState(SocialObject.HealthState.WILL_BE_INFECTED);
    // }
    // }
    // }
    // }

    private void updateTime() {
        values.put("time", values.get("time") + delta);
        labelObservers.get("time").update("Time: " + time());
    }

    public void updateLog(String text) {
        for (int i = 1; i < logs.length; ++i)
            logs[i - 1] = logs[i];
        logs[4] = time() + " - " + text;
        for (int i = 0; i < logs.length - 1; ++i)
            labelObservers.get(Integer.toString(i)).update(logs[i].length() > 1 ? logs[i] : " ");
        labelObservers.get("4").update(logs[4]);
    }

    private String time() {
        int time = values.get("time");
        int min = (int) ((float) time / 1000) / 60;
        int sec = (int) ((float) time / 1000) % 60;
        return String.format("%02d.%02d", min, sec);
    }

    private boolean isJustCollided(SocialObject a) {
        return a.getStandby() >= -1000;
    }

    public void paintField(Graphics2D g2d) {
        for (SocialObject socialObject : socialObjects) {
            socialObject.paint(g2d);
            // if (socialObject.inCanvas()) {
            // g2d.setColor(socialObject.getColor());
            // g2d.fillRect(socialObject.getX(), socialObject.getY(), objectSize,
            // objectSize);
            // }
        }
    }

    public void addObserver(String key, JLabel label) {
        labelObservers.put(key, new LabelObserver(label));
        if (values.containsKey(key))
            labelObservers.get(key).update(values.get(key));
    }

    public void updateLabel(String key, int x) {
        values.put(key, values.get(key) + x);
        // labelObservers.get(key).update(values.get(key));
        labelObservers.get(key).update(key.toUpperCase() + ": " + Integer.toString(values.get(key)));
    }

    public void updateLabel(String key, int x, String text) {
        values.put(key, values.get(key) + x);
        // labelObservers.get(key).update(values.get(key));
        labelObservers.get(key).update(key.toUpperCase() + ": " + Integer.toString(values.get(key)) + text);
    }

    private class LabelObserver {
        private JLabel label;

        public LabelObserver(JLabel label) {
            this.label = label;
        }

        public void update(int value) {
            label.setText(label.getText().replaceAll("\\d", "") + Integer.toString(value));
        }

        public void update(String text) {
            label.setText(text);
        }
    }

    private void changeState() {
        pause = !pause;
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        if (arg0.getKeyCode() == KeyEvent.VK_P) {
            changeState();
            if (pause) {
                labelObservers.get("state").update("PAUSED");
                labelObservers.get("info").update("Press P to Continue.");
            } else {
                labelObservers.get("state").update("");
                labelObservers.get("info").update("Press P to Pause.");
            }

        }

    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

    public boolean hospitalFull() {
        return getInHospital() == B;
    }

    public int getHospitalArrival() {
        return this.hospitalArrival;
    }

    public int getHospitalStandby() {
        return this.hospitalStandby;
    }

    public int getInHospital() {
        return values.get("hospitalized");
    }

    public void setInHospital(int x) {
        values.put("hospitalized", x);
    }

    public float getInfectedLifetime() {
        return this.infectedLifetime;
    }

}
