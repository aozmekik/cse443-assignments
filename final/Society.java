import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;

// Swing Program Template
@SuppressWarnings("serial")
public class Society extends JPanel {
    public static final int CANVAS_WIDTH = 1000;
    public static final int CANVAS_HEIGHT = 600;
    public static final String TITLE = "...Title...";
    private BufferedImage img;

    public Society() {
        img = new BufferedImage(CANVAS_WIDTH, CANVAS_HEIGHT, BufferedImage.TYPE_BYTE_BINARY);
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // paint background
        setBackground(Color.BLACK);
        toggleBlock(1, 0, 0, 50, 50);
        g.drawImage(img, 0, 0, null);
    }

    public void toggleBlock(int color, int x1, int y1, int x2, int y2) {
        if (color == 0) {
            color = Color.BLACK.getRGB();
        } else {
            color = Color.WHITE.getRGB();
        }
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                img.setRGB(x, y, color);
            }
        }
    }

    public void clear() {
        toggleBlock(0, 0, 0, img.getWidth() - 1, img.getHeight() - 1);
    }

    public static void test() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame(TITLE);
                frame.setContentPane(new Society());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}