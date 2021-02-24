
/**
 * Plotter represents the plot drawer and constructor for some given 
 * value range being y coordinate and x coordinate being the time interval
 * shared among the plotters.
 *
 * Applies Observer design pattern for to observe some changes social controller
 * such as pausing the program.
 * @see SocialController
 * @see Observer
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.awt.Graphics2D;
import java.awt.Color;

public class Plotter extends Observer {
    private static List<Integer> x = new ArrayList<Integer>();
    private List<Integer> y = new ArrayList<Integer>();
    private Map<String, Integer> values;
    private String key;
    private Boolean pause;

    public Plotter(SocietyController subject, Map<String, Integer> values, String key) {
        super(subject);
        subject.attach(this);
        this.values = values;
        this.key = key;
        pause = false;
    }

    public String getKey() {
        return this.key;
    }

    private int scaleBetween(float unscaledNum, float minAllowed, float maxAllowed, float min, float max) {
        return (int) ((maxAllowed - minAllowed) * (unscaledNum - min) / (max - min) + minAllowed);
    }

    private List<Integer> scalePlotY(List<Integer> plot, int min, int max) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < plot.size(); ++i)
            list.add(85 - scaleBetween(plot.get(i), 0, 200, min, max));
        return list;
    }

    /**
     * Plots the x, y coordinates to the given graphics object. Plotting is
     * simulated, lines as multiple circle drawings.
     */
    public void plot(Graphics2D g2d) {
        if (!pause) {
            if (x.size() <= 200) {
                if (x.isEmpty())
                    x.add(0);
                else
                    x.add(x.get(x.size() - 1) + 1);
            }

            if (y.size() >= 200) {
                for (int i = 0; i < y.size() - 1; ++i)
                    y.set(i, y.get(i + 1));
                y.set(y.size() - 1, values.get(key));
            } else
                y.add(values.get(key));

        }

        List<Integer> list = scalePlotY(y, 0, 500);
        g2d.setBackground(Color.BLACK);

        for (int i = 0; i < y.size(); ++i) {
            g2d.setColor(Color.WHITE);
            g2d.fillOval(x.get(i), list.get(i), 2, 2);
        }
    }

    @Override
    public void update() {
        pause = !pause;
    }

}