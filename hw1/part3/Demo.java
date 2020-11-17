public class Demo {
    public static void print(Suit suit) {
        System.out.println("\t>" + suit.description() + ": " + suit.cost() + "k TL, " + suit.weight() + "kg");
    }

    public static void main(String[] args) {
        Suit suit[] = new Suit[15];

        suit[0] = new Dec();
        suit[1] = new Ora();
        suit[2] = new Tor();

        System.out.println("\n{ --- Basic Suits --- } ");
        print(suit[0]);
        print(suit[1]);
        print(suit[2]);

        System.out.println("\n{ --- Enchanced Suits --- } ");
        suit[3] = new Flamethrower(suit[0]);
        suit[4] = new AutoRifle(suit[1]);
        suit[5] = new RocketLauncher(suit[2]);
        suit[6] = new Laser(suit[0]);

        print(suit[3]);
        print(suit[4]);
        print(suit[5]);
        print(suit[6]);

        System.out.println("\n{ --- Double Enchanced Suits --- }");
        suit[7] = new Laser(suit[3]);
        suit[8] = new RocketLauncher(suit[4]);
        suit[9] = new Flamethrower(suit[5]);
        suit[10] = new AutoRifle(suit[6]);

        print(suit[7]);
        print(suit[8]);
        print(suit[9]);
        print(suit[10]);

        System.out.println("\n{ --- Superflous Suits --- }");
        suit[11] = new Laser(new Flamethrower(new Laser(suit[7])));
        suit[12] = new AutoRifle(new Laser(new Flamethrower(suit[8])));
        suit[13] = new RocketLauncher(new Flamethrower(new AutoRifle(suit[9])));
        suit[14] = new Laser(new RocketLauncher(new AutoRifle(suit[10])));

        print(suit[11]);
        print(suit[12]);
        print(suit[13]);
        print(suit[14]);

    }
}
