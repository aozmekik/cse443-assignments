
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLabel;

import java.awt.GridLayout;
import javax.swing.BorderFactory;

public class SocietySimulator extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    private SocietyField societyField = new SocietyField(WIDTH, HEIGHT);

    public SocietySimulator() {
        super("Epidemic Simulation");
        setSize(WIDTH + 400, HEIGHT + 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        SocietyPanel societyPanel = new SocietyPanel(WIDTH, HEIGHT);
        societyPanel.setBorder(BorderFactory.createLineBorder(Color.red));
        JPanel leftPanel = new JPanel();
        leftPanel.setBorder(BorderFactory.createEmptyBorder(100, 200, 50, 200));
        leftPanel.setLayout(new GridLayout(1, 1));
        leftPanel.add(societyPanel);
        leftPanel.setBackground(Color.BLACK);
        add(leftPanel, BorderLayout.CENTER);
        getContentPane().add(new TextPanel(), BorderLayout.NORTH);
        getContentPane().add(new PausePanel(), BorderLayout.SOUTH);
        addKeyListener((KeyListener) societyField);
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

            societyField.update();
            societyField.paintField(g2d);

            g2d.dispose();
        }

    }

    class TextPanel extends JPanel {

        private static final long serialVersionUID = 1L;
        private Map<String, JLabel> labels = new HashMap<String, JLabel>();

        public TextPanel() {
            super();
            JPanel centerPanel = new JPanel(new GridLayout(0, 4));
            labels.put("infected", new JLabel("INFECTED: "));
            labels.put("healthy", new JLabel("HEALTHY: "));
            labels.put("hospitalized", new JLabel("HOSPITALIZED: "));
            labels.put("dead", new JLabel("DEAD: "));
            centerPanel.setBackground(Color.BLACK);

            Font font = new Font("Verdana", Font.BOLD, 16);
            for (String key : labels.keySet()) {
                JLabel label = labels.get(key);
                centerPanel.add(label);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setFont(font);
                label.setForeground(Color.WHITE);
                societyField.addObserver(key, label);
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
            JLabel labels[] = new JLabel[2];
            JPanel centerPanel = new JPanel(new GridLayout(2, 0));
            labels[0] = new JLabel("PAUSED");
            labels[1] = new JLabel("Press P to Continue.");
            centerPanel.setBackground(Color.BLACK);

            Font font = new Font("Verdana", Font.BOLD, 24);

            for (JLabel label : labels) {
                centerPanel.add(label);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setFont(font);
                label.setForeground(Color.WHITE);
            }

            societyField.addObserver("state", labels[0]);
            societyField.addObserver("info", labels[1]);

            int gap = 5;
            setLayout(new BorderLayout(gap, gap));
            setBorder(BorderFactory.createEmptyBorder(gap, gap, gap, gap));
            add(centerPanel, BorderLayout.CENTER);
            setBackground(Color.BLACK);
        }

    }

}