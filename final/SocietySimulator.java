
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.SpringLayout;

public class SocietySimulator extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    private SocietyController sc = new SocietyController(WIDTH, HEIGHT);

    private Font font = new Font("Verdana", Font.BOLD, 16);

    public SocietySimulator() {
        super("Epidemic Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(Color.BLACK);

        setLayout(new BorderLayout(25, 25));
        setSize(WIDTH + 200, HEIGHT + 300);

        SocietyPanel societyPanel = new SocietyPanel(WIDTH, HEIGHT);
        societyPanel.setBorder(BorderFactory.createLineBorder(Color.RED));

        add(societyPanel, BorderLayout.CENTER);
        add(new TextPanel(), BorderLayout.NORTH);
        add(new PausePanel(), BorderLayout.SOUTH);
        add(new RightPanel(), BorderLayout.EAST);
        add(new LeftPanel(), BorderLayout.WEST);

        pack();
        setVisible(true);
    }

    class SocietyPanel extends JPanel {

        private static final long serialVersionUID = 1L;

        public SocietyPanel(int width, int height) {
            super();
            setPreferredSize(new Dimension(width, height));

            Timer timer = new Timer(30, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    repaint();
                    Toolkit.getDefaultToolkit().sync();
                }
            });
            timer.start();

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            super.setBackground(Color.BLACK);

            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
                    RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

            sc.update();
            sc.paint(g2d);

            g2d.dispose();
        }

    }

    class TextPanel extends JPanel {

        private static final long serialVersionUID = 1L;
        private Map<String, JLabel> labels = new HashMap<String, JLabel>();

        public TextPanel() {
            super();
            JPanel centerPanel = new JPanel(new GridLayout(0, 6));
            labels.put("ventilator", new JLabel("POPULATION: "));
            labels.put("population", new JLabel("POPULATION: "));
            labels.put("infected", new JLabel("INFECTED: "));
            labels.put("healthy", new JLabel("HEALTHY: "));
            labels.put("hospitalized", new JLabel("HOSPITALIZED: "));
            labels.put("dead", new JLabel("DEAD: "));
            centerPanel.setBackground(Color.BLACK);

            for (String key : labels.keySet()) {
                JLabel label = labels.get(key);
                centerPanel.add(label);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setFont(font);
                label.setForeground(Color.WHITE);
                sc.addLabelObserver(key, label);
            }

            int gap = 5;
            setLayout(new BorderLayout(gap, gap));
            setBorder(BorderFactory.createEmptyBorder(gap, gap, gap, gap));
            add(centerPanel, BorderLayout.PAGE_END);
            setBackground(Color.BLACK);
        }
    }

    class PausePanel extends JPanel {

        private static final long serialVersionUID = 1L;

        public PausePanel() {
            super();
            JLabel labels[] = new JLabel[1];
            JPanel centerPanel = new JPanel(new GridLayout(1, 0));
            labels[0] = new JLabel("Time: ");
            centerPanel.setBackground(Color.BLACK);

            for (JLabel label : labels) {
                centerPanel.add(label);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setFont(font);
                label.setForeground(Color.WHITE);
            }
            sc.addLabelObserver("time", labels[0]);

            int gap = 1;
            setLayout(new BorderLayout(gap, gap));
            setBorder(BorderFactory.createEmptyBorder(gap, gap, gap, gap));
            add(centerPanel, BorderLayout.EAST);
            add(new LogPanel(), BorderLayout.WEST);
            setBackground(Color.BLACK);
        }

    }

    class LogPanel extends JPanel {

        private static final long serialVersionUID = 1L;

        public LogPanel() {
            super();
            JLabel labels[] = new JLabel[5];
            JPanel centerPanel = new JPanel(new GridLayout(5, 0));
            centerPanel.setBackground(Color.BLACK);

            for (int i = 0; i < labels.length; ++i) {
                labels[i] = new JLabel(" ");
                centerPanel.add(labels[i]);
                labels[i].setFont(font);
                labels[i].setForeground(Color.WHITE);
                sc.addLabelObserver(String.format("%d", i), labels[i]);
            }

            labels[4].setForeground(Color.RED);

            int gap = 1;
            setLayout(new BorderLayout(gap, gap));
            setBorder(BorderFactory.createEmptyBorder(gap, gap, gap, gap));
            add(centerPanel, BorderLayout.CENTER);
            setBackground(Color.BLACK);
        }

    }

    class RightPanel extends JPanel {

        private static final long serialVersionUID = 1L;

        public RightPanel() {
            super();
            JPanel centerPanel = new JPanel(new GridLayout(5, 0, 50, 50));
            JPanel controlButtons = new JPanel(new FlowLayout());

            JButton buttons[] = new JButton[2];
            buttons[0] = new JButton("ADD INFECTED");
            buttons[1] = new JButton("ADD HEALTHY");

            sc.assignButtonHandlers("addInfected", buttons[0]);
            sc.assignButtonHandlers("addHealthy", buttons[1]);

            centerPanel.setBackground(Color.BLACK);
            controlButtons.setBackground(Color.BLACK);

            for (int i = 0; i < 2; ++i) {
                controlButtons.add(buttons[i]);
                buttons[i].setFont(font);
                buttons[i].setForeground(Color.BLACK);
                buttons[i].setBackground(Color.WHITE);
            }

            PlotPanel plotPanel1 = new PlotPanel(100, 100, sc.getHealthyPlotter());
            PlotPanel plotPanel2 = new PlotPanel(100, 100, sc.getInfectedPlotter());
            plotPanel1.setBorder(BorderFactory.createLineBorder(Color.RED));
            plotPanel2.setBorder(BorderFactory.createLineBorder(Color.RED));

            centerPanel.add(controlButtons);
            centerPanel.add(plotPanel1);
            centerPanel.add(plotPanel2);

            int gap = 25;
            setLayout(new BorderLayout(gap, gap));
            setBorder(BorderFactory.createEmptyBorder(gap, gap, gap, gap));
            centerPanel.setPreferredSize(new Dimension(250, 100));
            add(centerPanel);
            setBackground(Color.BLACK);
        }

    }

    class LeftPanel extends JPanel {

        private static final long serialVersionUID = 1L;

        public LeftPanel() {
            super();
            JPanel centerPanel = new JPanel(new GridLayout(3, 0, 50, 50));
            JPanel controlButtons = new JPanel(new FlowLayout());
            JPanel labelPanel = new JPanel(new FlowLayout(0, 30, 30));

            JPanel addressPanel = new JPanel();
            Border border = addressPanel.getBorder();
            Border margin = new EmptyBorder(10, 10, 10, 10);
            addressPanel.setBorder(new CompoundBorder(border, margin));

            JButton buttons[] = new JButton[4];
            buttons[0] = new JButton("START");
            buttons[1] = new JButton("PAUSE");
            buttons[2] = new JButton("CLEAR");
            buttons[3] = new JButton("APPLY OPTIONS");

            JLabel labels[] = new JLabel[2];
            labels[0] = new JLabel("Mortality Rate: ");
            labels[1] = new JLabel("Spreading Factor: ");

            sc.assignButtonHandlers("start", buttons[0]);
            sc.assignButtonHandlers("pause", buttons[1]);
            sc.assignButtonHandlers("clear", buttons[2]);
            sc.assignButtonHandlers("apply", buttons[3]);

            for (int i = 0; i < labels.length; ++i) {
                labelPanel.add(labels[i]);
                labels[i].setFont(font);
                labels[i].setForeground(Color.WHITE);
            }

            sc.addLabelObserver("mortality", labels[0]);
            sc.addLabelObserver("spreading", labels[1]);

            centerPanel.setBackground(Color.BLACK);
            controlButtons.setBackground(Color.BLACK);

            GridBagLayout panelGridBagLayout = new GridBagLayout();
            panelGridBagLayout.columnWidths = new int[] { 86, 86, 0 };
            panelGridBagLayout.rowHeights = new int[] { 20, 20, 20, 20, 20, 0 };
            panelGridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
            panelGridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
            addressPanel.setLayout(panelGridBagLayout);

            sc.assignSliderHandlers("P", addLabelAndSlider("Population:        ", 0, addressPanel, 0, 1000, 100, 500));
            sc.assignSliderHandlers("mortality",
                    addLabelAndSlider("Mortality Rate [0.1, 0.9]:   ", 1, addressPanel, 10, 90, 5, 10));
            sc.assignSliderHandlers("spreading",
                    addLabelAndSlider("Spreading Factor [0.5, 1.0]: ", 2, addressPanel, 50, 100, 5, 10));

            centerPanel.add(addressPanel);
            centerPanel.add(controlButtons);
            centerPanel.add(labelPanel);
            addressPanel.setBackground(Color.BLACK);
            labelPanel.setBackground(Color.BLACK);
            setBackground(Color.BLACK);

            for (int i = 0; i < 4; ++i) {
                controlButtons.add(buttons[i]);
                buttons[i].setFont(font);
                buttons[i].setForeground(Color.BLACK);
                buttons[i].setBackground(Color.WHITE);
            }

            add(centerPanel);
            setBackground(Color.BLACK);
        }

    }

    private JSlider addLabelAndSlider(String labelText, int yPos, Container containingPanel, int min, int max,
            int minTick, int maxTick) {

        JLabel label = new JLabel(labelText);
        label.setFont(font);
        label.setForeground(Color.WHITE);
        GridBagConstraints gridBagConstraintForLabel = new GridBagConstraints();
        gridBagConstraintForLabel.fill = GridBagConstraints.BOTH;
        gridBagConstraintForLabel.insets = new Insets(0, 0, 5, 5);
        gridBagConstraintForLabel.gridx = 0;
        gridBagConstraintForLabel.gridy = yPos;
        containingPanel.add(label, gridBagConstraintForLabel);

        JSlider slider = new JSlider(min, max);
        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        if (maxTick == 0)
            slider.setSnapToTicks(true);
        slider.setMajorTickSpacing(maxTick);
        slider.setMinorTickSpacing(minTick);

        slider.setBackground(Color.BLACK);
        GridBagConstraints gridBagConstraintForTextField = new GridBagConstraints();
        gridBagConstraintForTextField.fill = GridBagConstraints.BOTH;
        gridBagConstraintForTextField.insets = new Insets(0, 0, 5, 0);
        gridBagConstraintForTextField.gridx = 1;
        gridBagConstraintForTextField.gridy = yPos;
        containingPanel.add(slider, gridBagConstraintForTextField);
        return slider;
    }

   
    class PlotPanel extends JPanel {

        private static final long serialVersionUID = 1L;
        private Plotter plotter;

        public PlotPanel(int width, int height, Plotter plotter) {
            super();
            setPreferredSize(new Dimension(width, height));
            this.plotter = plotter;

            Timer timer = new Timer(30, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    repaint();
                    Toolkit.getDefaultToolkit().sync();
                }
            });
            timer.start();
            
            JTextField textField = new JTextField(plotter.getKey() + "/time");
            textField.setBackground( new Color(255, 0, 0, 20));
            textField.setFont(new Font("Verdana", Font.PLAIN, 12));
            textField.setBorder(BorderFactory.createEmptyBorder());
            textField.setForeground(Color.WHITE);
            add(textField, BorderLayout.EAST);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            super.setBackground(Color.BLACK);

            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
                    RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

            plotter.plot(g2d);
            g2d.dispose();
        }

    }

}