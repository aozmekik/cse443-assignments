import java.awt.Graphics2D;
import java.util.Random;

public class SocietyField {
    private final int objectSize = 5;

    private SocialObject[] socialObjects = new SocialObject[30];
    int xlower, xupper, ylower, yupper;

    private Random random = new Random();

    public SocietyField(int width, int height) {

        for (int i = 0; i < socialObjects.length; i++)
            socialObjects[i] = new SocialObject(width, height);

        // setting canvas boundaries
        xlower = 0;
        xupper = width - objectSize;
        ylower = 0;
        yupper = height - objectSize;

        // random infected among the population
        socialObjects[random.nextInt(socialObjects.length)].setHealthState(SocialObject.HealthState.SICK);
    }

    public void update() {
        for (SocialObject socialObject : socialObjects)
            socialObject.update(xlower, xupper, ylower, yupper);

        for (int i = 0; i < socialObjects.length; i++)
            for (int j = i + 1; j < socialObjects.length; j++)
                socialObjects[i].checkCollision(socialObjects[j]);

    }

    public void paint(Graphics2D g2d) {
        for (SocialObject object : socialObjects)
            object.paint(g2d);
    }
}
