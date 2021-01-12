
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO. this is your save.
// TODO. 1. refactor this again.
// TODO. 

// public class SocietyPanel extends JPanel {

//     private static final long serialVersionUID = 1L;
//     private SocietyField societyField;

//     public SocietyPanel(int width, int height) {
//         setPreferredSize(new Dimension(width, height));
//         societyField = new SocietyField(width, height);


//         Timer timer = new Timer(20, new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 societyField.update();
//                 repaint();
//             }
//         });
//         timer.start();

//     }

//     @Override
//     protected void paintComponent(Graphics g) {
//         super.paintComponent(g);

//         super.setBackground(Color.BLACK);
//         // g.setColor(Color.BLACK);
//         // g.fillRect(0, 0, getWidth(), getHeight());

//         Graphics2D g2d = (Graphics2D) g.create();
//         g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
//         g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//         g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
//         g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
//         g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
//         g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//         g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//         g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
//         societyField.paint(g2d);
//         g2d.dispose();
//     }

// }