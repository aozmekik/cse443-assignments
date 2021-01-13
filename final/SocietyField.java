import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.Rectangle;

import javax.swing.JLabel;

public class SocietyField implements KeyListener {
    private final int objectSize = 5;
    private final int societySize = 30;

    private SocialObject[] socialObjects = new SocialObject[societySize];
    int xlower, xupper, ylower, yupper;

    private Random random = new Random();

    private static final int fps = 30;

    private Map<String, LabelObserver> labelObservers = new HashMap<String, LabelObserver>();
    private Map<String, Integer> values = new HashMap<String, Integer>();

    private boolean pause = false;

    public SocietyField(int width, int height) {

        for (int i = 0; i < socialObjects.length; i++)
            socialObjects[i] = new SocialObject(width, height);

        values.put("infected", 1);
        values.put("healthy", societySize - 1);
        values.put("hospitalized", 0);
        values.put("dead", 0);

        // setting canvas boundaries
        xlower = 0;
        xupper = width - objectSize;
        ylower = 0;
        yupper = height - objectSize;

        // random infected among the population
        socialObjects[random.nextInt(socialObjects.length)].setHealthState(SocialObject.HealthState.INFECTED);
    }

    public void update() {
        if (!pause) {
            for (SocialObject socialObject : socialObjects)
                move(socialObject);

            for (int i = 0; i < socialObjects.length; i++)
                for (int j = i + 1; j < socialObjects.length; j++)
                    checkCollision(socialObjects[i], socialObjects[j]);
        }

    }

    public void move(SocialObject a) {

        a.setStandby(a.getStandby() - fps);
        if (!a.onStandby()) {
            if (a.getHealthState() == SocialObject.HealthState.WILL_BE_INFECTED) {
                a.setHealthState(SocialObject.HealthState.INFECTED);
                updateLabel("healthy", false);
                updateLabel("infected", true);
            }

            int x = a.getX();
            int y = a.getY();
            int dx = a.getDx();
            int dy = a.getDy();
            int S = a.getS();

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

            a.setX(x);
            a.setY(y);
            a.setDx(dx);
            a.setDy(dy);
        }

    }

    public void checkCollision(SocialObject a, SocialObject b) {
        if (!isJustCollided(a) && !isJustCollided(b)) {

            int dist = Math.min(a.getD(), b.getD()) + objectSize;
            Rectangle r1 = new Rectangle(a.getX(), a.getY(), dist, dist);
            Rectangle r2 = new Rectangle(b.getX(), b.getY(), dist, dist);

            if (r1.intersects(r2)) {
                // in miliseconds.
                int time = (Math.max(a.getC(), b.getC())) * 1000;

                a.setStandby(time);
                b.setStandby(time);

                int dx = a.getDx();
                int dy = a.getDy();
                a.setDx(dx == b.getDx() ? -dx : dx);
                a.setDy(dy == b.getDy() ? -dy : dy);

                if (a.isInfected()) {
                    // float P = infectingProb(other);
                    // System.out.println(P);
                    // if (infectingProb(other) > 0.5F)
                    b.setHealthState(SocialObject.HealthState.WILL_BE_INFECTED);

                } else if (b.isInfected()) {
                    // float P = infectingProb(other);
                    // System.out.println(P);
                    // if (other.infectingProb(this) > 0.5F)
                    a.setHealthState(SocialObject.HealthState.WILL_BE_INFECTED);
                }
            }
        }

    }

    public boolean isJustCollided(SocialObject a) {
        return a.getStandby() >= -3 * fps;
    }

    public void paintField(Graphics2D g2d) {
        for (SocialObject socialObject : socialObjects) {
            g2d.setColor(socialObject.getColor());
            g2d.fillRect(socialObject.getX(), socialObject.getY(), objectSize, objectSize);
        }
    }

    public void addObserver(String key, JLabel label) {
        labelObservers.put(key, new LabelObserver(label));
        if (values.containsKey(key))
            labelObservers.get(key).update(values.get(key));
    }

    public void updateLabel(String key, boolean increase) {
        values.put(key, increase ? values.get(key) + 1 : values.get(key) - 1);
        labelObservers.get(key).update(values.get(key));
    }

    public class LabelObserver {
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

    public void changeState() {
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

}
