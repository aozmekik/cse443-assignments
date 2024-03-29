public class Driver {
    public static void main(String[] args) {
        HiTech hiTech = new HiTech();

        // Demo for state design pattern working correctly.

        TrafficLight trafficLight = new TrafficLight(hiTech);

        System.out.println("SUCCESSFULL PATH");
        System.out.println("\n-----\tSTART\t-----\n");
        System.out.println("\t{\tred->green->yellow->red\t}");
        trafficLight.toGreen();
        trafficLight.toYellow();
        trafficLight.toRed();
        System.out.println("\n-----\tEND\t-----\n");

        System.out.println("UNSUCCESSFULL PATHS");
        System.out.println("\n-----\tSTART\t-----\n");
        System.out.println("\t{\tred->yellow\t}");
        trafficLight.reset();
        trafficLight.toYellow();
        System.out.println("\n-----\tEND\t-----\n");

        System.out.println("\n-----\tSTART\t-----\n");
        System.out.println("\t{\tred->red\t}");
        trafficLight.reset();
        trafficLight.toRed();
        System.out.println("\n-----\tEND\t-----\n");

        System.out.println("\n-----\tSTART\t-----\n");
        System.out.println("\t{\tred->green->red\t}");
        trafficLight.reset();
        trafficLight.toGreen();
        trafficLight.toRed();
        System.out.println("\n-----\tEND\t-----\n");

        System.out.println("\n-----\tSTART\t-----\n");
        System.out.println("\t{\tred->green->green\t}");
        trafficLight.reset();
        trafficLight.toGreen();
        trafficLight.toGreen();
        System.out.println("\n-----\tEND\t-----\n");

        System.out.println("\n-----\tSTART\t-----\n");
        System.out.println("\t{\tred->green->yellow->yellow\t}");
        trafficLight.reset();
        trafficLight.toGreen();
        trafficLight.toYellow();
        trafficLight.toYellow();
        System.out.println("\n-----\tEND\t-----\n");

        System.out.println("\n-----\tSTART\t-----\n");
        System.out.println("\t{\tred->green->yellow->green\t}");
        trafficLight.reset();
        trafficLight.toGreen();
        trafficLight.toYellow();
        trafficLight.toGreen();
        System.out.println("\n-----\tEND\t-----\n");

        // Demo for observer design pattern working correctly.

        hiTech.changeDetected(true);
        System.out.println("SUCCESSFULL PATH");
        System.out.println("\n-----\tSTART\t-----\n");
        System.out.println("\t{\tred->green->yellow->red\t}");
        trafficLight.reset();
        trafficLight.toGreen();
        trafficLight.toYellow();
        trafficLight.toRed();
        System.out.println("\n-----\tEND\t-----\n");

        hiTech.changeDetected(false);
        System.out.println("SUCCESSFULL PATH");
        System.out.println("\n-----\tSTART\t-----\n");
        System.out.println("\t{\tred->green->yellow->red\t}");
        trafficLight.reset();
        trafficLight.toGreen();
        trafficLight.toYellow();
        trafficLight.toRed();
        System.out.println("\n-----\tEND\t-----\n");
    }
}
