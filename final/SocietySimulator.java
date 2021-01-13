
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.GridLayout;
import javax.swing.BorderFactory;


public class SocietySimulator {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;

    public SocietySimulator() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                JFrame frame = new JFrame("Epidemic Simulation");
                frame.setSize(WIDTH + 400, HEIGHT + 200);
                frame.getContentPane().setLayout(new GridLayout(1, 1));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);

                SocietyPanel societyPanel = new SocietyPanel(WIDTH, HEIGHT);
                // frame.add(societyPanel);
                societyPanel.setBorder(BorderFactory.createLineBorder(Color.red));
                // ((TitledBorder) societyPanel.getBorder()).setTitleFont(new Font("Arial", Font.BOLD, 20));
                // frame.pack();
                JPanel leftPanel = new JPanel();
                leftPanel.setBorder(BorderFactory.createEmptyBorder(100, 200, 75, 200));
                leftPanel.setLayout(new GridLayout(1, 1));
                leftPanel.add(societyPanel);
                frame.getContentPane().add(leftPanel);
                frame.setVisible(true);
            }
        });
    }

    class SocietyPanel extends JPanel {

        private static final long serialVersionUID = 1L;
        private SocietyField societyField;

        public SocietyPanel(int width, int height) {
            setPreferredSize(new Dimension(width, height));
            societyField = new SocietyField(width, height);

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
            societyField.paint(g2d);

            g2d.dispose();
        }

    }
}