
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
    private SocietyController sc = new SocietyController(WIDTH, HEIGHT);

    public SocietySimulator() {
        super("Epidemic Simulation");
        setSize(WIDTH + 400, HEIGHT + 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        SocietyPanel societyPanel = new SocietyPanel(WIDTH, HEIGHT);
        societyPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
        JPanel leftPanel = new JPanel();
        leftPanel.setBorder(BorderFactory.createEmptyBorder(100, 200, 50, 200));
        leftPanel.setLayout(new GridLayout(1, 1));
        leftPanel.add(societyPanel);
        leftPanel.setBackground(Color.BLACK);
        add(leftPanel, BorderLayout.CENTER);
        getContentPane().add(new TextPanel(), BorderLayout.NORTH);
        getContentPane().add(new PausePanel(), BorderLayout.SOUTH);
        // getContentPane().add(new LogPanel(), BorderLayout.WEST);
        addKeyListener((KeyListener) sc);
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
                sc.addObserver(key, label);
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
            JLabel labels[] = new JLabel[3];
            JPanel centerPanel = new JPanel(new GridLayout(3, 0));
            labels[0] = new JLabel("");
            labels[1] = new JLabel("Time: ");
            labels[2] = new JLabel("Press P to Pause.");
            centerPanel.setBackground(Color.BLACK);

            Font font = new Font("Verdana", Font.BOLD, 16);

            for (JLabel label : labels) {
                centerPanel.add(label);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setFont(font);
                label.setForeground(Color.WHITE);
            }

            sc.addObserver("state", labels[0]);
            sc.addObserver("info", labels[2]);
            sc.addObserver("time", labels[1]);

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
            labels[0] = new JLabel("");
            labels[1] = new JLabel("Time: ");
            labels[2] = new JLabel("Press P to Pause.");
            centerPanel.setBackground(Color.BLACK);

            Font font = new Font("Verdana", Font.BOLD, 16);

            for (int i = 0; i < labels.length; ++i) {
                labels[i] = new JLabel(" ");
                centerPanel.add(labels[i]);
                // labels[i].setHorizontalAlignment(JLabel.CENTER);
                labels[i].setFont(font);
                labels[i].setForeground(Color.WHITE);
                sc.addObserver(String.format("%d", i), labels[i]);
            }

            labels[4].setForeground(Color.RED);

            int gap = 1;
            setLayout(new BorderLayout(gap, gap));
            setBorder(BorderFactory.createEmptyBorder(gap, gap, gap, gap));
            add(centerPanel, BorderLayout.CENTER);
            setBackground(Color.BLACK);
        }

    }

}