import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SocietyController implements Mediator {
    private final int objectSize = 5;
    private int P = 100;
    private int B = P / 100; // B = P / 100
    private float R = 0.5F + (new Random().nextFloat() * 0.5F); // disease spreading factor [0.5, 1.0]
    private float Z = 0.1F + (new Random().nextFloat() * 0.8F); // disease mortality rate [0.1, 0.9]
    private final int hospitalArrival = 25000; // 25 sec.
    private final int hospitalStandby = 10000; // 10 sec

    private List<SocialObject> socialObjects = new ArrayList<SocialObject>();
    private List<SocialObject> deads = new ArrayList<SocialObject>();
    

    private Random random = new Random();

    private static final int delta = 30;

    private Map<String, LabelObserver> labelObservers = new HashMap<String, LabelObserver>();
    private Map<String, ButtonObserver> buttonObservers = new HashMap<String, ButtonObserver>();
    private Map<String, SliderObserver> sliderObservers = new HashMap<String, SliderObserver>();

    private Map<String, Integer> values = new HashMap<String, Integer>();

    private boolean pause = false;
    private float infectedLifetime;
    private String logs[] = new String[5];

    private Map<String, ActionListener> buttonHandlers = new HashMap<String, ActionListener>();
    private Map<String, ChangeListener> sliderHandlers = new HashMap<String, ChangeListener>();
    private final int width, height;

    private Plotter healthyPlotter = new Plotter(values, "healthy");
    private Plotter infectedPlotter = new Plotter(values, "infected");


    public SocietyController(int width, int height) {
        this.width = width;
        this.height = height;

        for (int i = 0; i < P; i++)
            socialObjects.add(new SocialObject(this, width, height));

        values.put("population", P);
        values.put("infected", 1);
        values.put("healthy", P - 1);
        values.put("hospitalized", 0);
        values.put("ventilator", B);
        values.put("dead", 0);
        values.put("time", 0);
        values.put("P", P);
        values.put("mortality", (int) (Z * 100));
        values.put("spreading", (int) (R * 100));

        buttonHandlers.put("start", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                pause = false;
                buttonObservers.get("start").setEnabled(false);
                buttonObservers.get("pause").setEnabled(true);
                updateLog("Continued.");
            }
        });

        buttonHandlers.put("pause", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                pause = true;
                buttonObservers.get("start").setEnabled(true);
                buttonObservers.get("pause").setEnabled(false);
                updateLog("Paused.");
            }
        });

        buttonHandlers.put("clear", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                values.put("population", 0);
                values.put("infected", 0);
                values.put("healthy", 0);
                values.put("hospitalized", 0);
                values.put("dead", 0);
                values.put("time", 0);
                updateLabel("population", 0);
                updateLabel("infected", 0);
                updateLabel("healthy", 0);
                updateLabel("hospitalized", 0);
                updateLabel("dead", 0);
                updateLabel("time", 0);
                socialObjects.clear();
            }
        });

        buttonHandlers.put("addInfected", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                addIndividiual(1, true);
                updateLog("Added 1 infected individual.");
            }
        });

        buttonHandlers.put("addHealthy", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                addIndividiual(1, false);
                updateLog("Added 1 healthy individual.");
            }
        });

        buttonHandlers.put("apply", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                R = (float) values.get("spreading") / 100;
                Z = (float) values.get("mortality") / 100;

                updateLabel("spreading", 0);
                updateLabel("mortality", 0);

                addIndividiual(values.get("P"), false);
                updateLog("Update the Spreading Factor.");
                updateLog("Update the Mortality Rate.");
                updateLog(String.format("Added %d healthy individual", values.get("P")));
            }
        });

        for (String key : new ArrayList<String>(Arrays.asList("P,mortality,spreading".split(",")))) {
            sliderHandlers.put(key, new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent arg0) {
                    values.put(key, sliderObservers.get(key).getValue());

                }
            });

        }

        HealthyState.setSettings(objectSize, width, height, this);

        for (int i = 0; i < logs.length; ++i)
            logs[i] = " ";

        infectedLifetime = (100 * (1 - Z)) * 1000;

        // random infected among the population
        SocialObject so = socialObjects.get(random.nextInt(P));
        so.setState(so.getInfectedState());
    }

    public void update() {
        if (!pause) {
            updateTime();

            for (SocialObject socialObject : socialObjects)
                socialObject.update(delta);

            socialObjects.removeAll(deads);

            for (int i = 0; i < socialObjects.size(); i++)
                for (int j = i + 1; j < socialObjects.size(); j++)
                    socialObjects.get(i).checkCollision(socialObjects.get(j));
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

    public void addIndividiual(int x, boolean infected) {
        for (int i = 0; i < x; i++) {
            updateLabel("population", 1);
            socialObjects.add(new SocialObject(this, width, height));
            SocialObject last = socialObjects.get(socialObjects.size() - 1);
            if (infected) {
                last.setState(last.getInfectedState());
                updateLabel("infected", 1);
            } else
                updateLabel("healthy", 1);
        }
        values.put("ventilator", values.get("population") / 100);
        updateLabel("ventilator", 0);
        B = values.get("ventilator");
    }

    public void assignButtonHandlers(String key, JButton button) {
        button.addActionListener(buttonHandlers.get(key));
        buttonObservers.put(key, new ButtonObserver(button));
    }

    public void assignSliderHandlers(String key, JSlider slider) {
        slider.addChangeListener(sliderHandlers.get(key));
        sliderObservers.put(key, new SliderObserver(slider));
        sliderObservers.get(key).setValue(values.get(key));
    }

    public void addLabelObserver(String key, JLabel label) {
        labelObservers.put(key, new LabelObserver(label));
        if (values.containsKey(key))
            updateLabel(key, 0);
    }

    public void updateLabel(String key, int x) {
        values.put(key, values.get(key) + x);
        if (key == "spreading" || key == "mortality") {
            float realValue = ((float) values.get(key)) / 100;
            labelObservers.get(key).update(key.toUpperCase() + ": " + String.format("%.2f", realValue));
        } else
            labelObservers.get(key).update(key.toUpperCase() + ": " + Integer.toString(values.get(key)));
    }

    public void updateLabel(String key, int x, String text) {
        values.put(key, values.get(key) + x);
        labelObservers.get(key).update(key.toUpperCase() + ": " + Integer.toString(values.get(key)) + text);
    }

    public void toDead(SocialObject socialObject) {
        deads.add(socialObject);
    }

    private class LabelObserver {
        private JLabel label;

        public LabelObserver(JLabel label) {
            this.label = label;
        }

        public void update(String text) {
            label.setText(text);
        }
    }

    private class ButtonObserver {
        private JButton button;

        public ButtonObserver(JButton button) {
            this.button = button;
        }

        public void setEnabled(boolean enable) {
            button.setEnabled(enable);
        }
    }

    private class SliderObserver {
        private JSlider slider;

        public SliderObserver(JSlider slider) {
            this.slider = slider;
        }

        public int getValue() {
            return slider.getValue();
        }

        public void setValue(int x) {
            slider.setValue(x);
        }
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


    public Plotter getInfectedPlotter()
    {
        return infectedPlotter;
    }

    public Plotter getHealthyPlotter()
    {
        return healthyPlotter;
    }

   

   
}
