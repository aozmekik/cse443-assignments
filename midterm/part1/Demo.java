
/**
 * Demo. Demonstration of the whole bag of Phone factory package which is an
 * instance of naive implementation of Abstract Factory Design Pattern.
 */

public class Demo {

    public static void main(String[] args) {
        Phone phones[] = new Phone[10];
        String models[] = { "maximumeffort", "iflasdeluxe", "iiamaniflas" };
        String regions[] = { "Turkey", "EU", "Global" };

        int i = 0;
        for (PhoneFactory.Region region : PhoneFactory.Region.values()) {
            AbstractPhoneFactory phoneFactory = new PhoneFactory(region);
            System.out.println("on Region: " + regions[i / 3]);
            for (String model : models) {
                phones[i] = phoneFactory.createPhone(model);
                i++;
            }
        }

    }
}
