import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.Thread.State;

import javax.swing.JLabel;

// TODO. start satisfying the goals.
// TODO. last thing is add gui elements for modify.I

// TODO. Mediator
// TODO. Producer/Consumer?
// TODO. flexible gui adding them one by one & bulk.

public class SocietyController implements KeyListener, Mediator {
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
    private String logs[] = new String[5];

    public SocietyController(int width, int height) {

        for (int i = 0; i < socialObjects.length; i++)
            socialObjects[i] = new SocialObject(this, width, height);

        values.put("infected", 1);
        values.put("healthy", P - 1);
        values.put("hospitalized", 0);
        values.put("dead", 0);
        values.put("time", 0);

        HealthyState.setSettings(objectSize, width, height, this);

        for (int i = 0; i < logs.length; ++i)
            logs[i] = " ";

        infectedLifetime = (100 * (1 - Z)) * 1000;

        // random infected among the population
        SocialObject so = socialObjects[random.nextInt(socialObjects.length)];
        so.setState(so.getInfectedState());
    }

    public void update() {
        if (!pause) {
            updateTime();

            for (SocialObject socialObject : socialObjects)
                socialObject.update(delta);

            for (int i = 0; i < socialObjects.length; i++)
                for (int j = i + 1; j < socialObjects.length; j++)
                    socialObjects[i].checkCollision(socialObjects[j]);
        }

    }

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

    public void paint(Graphics2D g2d) {
        for (SocialObject socialObject : socialObjects)
            socialObject.paint(g2d);
    }

    public void addObserver(String key, JLabel label) {
        labelObservers.put(key, new LabelObserver(label));
        if (values.containsKey(key))
            labelObservers.get(key).update(values.get(key));
    }

    public void updateLabel(String key, int x) {
        values.put(key, values.get(key) + x);
        labelObservers.get(key).update(key.toUpperCase() + ": " + Integer.toString(values.get(key)));
    }

    public void updateLabel(String key, int x, String text) {
        values.put(key, values.get(key) + x);
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

    @Override
    public void notify(SocialObject so, SocialObject other, String event) {
        if (event == "collision") {
            int time = (Math.max(so.getC(), other.getC())) * 1000; // in miliseconds.

            StandbyState standbyState;
            // so is healthy and other is infected. so gets infected.
            if (so.getState() == so.getHealthyState() && other.isInfected()) {
                standbyState = (StandbyState) other.getStandbyState();
                standbyState.setMoveOnState(standbyState.getInfectedMoveOnState());

                standbyState = (StandbyState) so.getStandbyState();
                standbyState.setMoveOnState(standbyState.getWillbeInfectedMoveOnState());
            } 
            // so is infected. and if other is healthy other gets infected.
            else if (so.isInfected()) {
                standbyState = (StandbyState) so.getStandbyState();
                standbyState.setMoveOnState(standbyState.getInfectedMoveOnState());

                standbyState = (StandbyState) other.getStandbyState();
                if (other.isInfected())
                    standbyState.setMoveOnState(standbyState.getInfectedMoveOnState());
                else
                    standbyState.setMoveOnState(standbyState.getWillbeInfectedMoveOnState());

            }

            so.setStandby(time);
            other.setStandby(time);
            so.setState(so.getStandbyState());
            other.setState(other.getStandbyState());

            int dx = so.getDx();
            int dy = so.getDy();
            so.setDx(dx == other.getDx() ? -dx : dx);
            so.setDy(dy == other.getDy() ? -dy : dy);
        }

    }

}
