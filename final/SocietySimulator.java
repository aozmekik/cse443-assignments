
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SocietySimulator {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    private static final int NUMSocialObjectS = 30;
    private SocialObject[] socialObjects = new SocialObject[NUMSocialObjectS];

    public SocietySimulator() {
        for (int i = 0; i < socialObjects.length; i++) {
            socialObjects[i] = new SocialObject(WIDTH, HEIGHT);
        }

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                        | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Epidemic Simulation");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new SocietyPanel(socialObjects, WIDTH, HEIGHT));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}