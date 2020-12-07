import Battery.Battery;
import Camera.Camera;
import Case.Case;
import CpuAndRam.CpuAndRam;
import Display.Display;
import Storage.Storage;

/**
 * Phone. Represent the Phone backbone. Has different kind of ingredients which
 * can be from any instance. Provides and simple interface for phone production.
 */

public abstract class Phone {
    protected String name;

    protected Display display;
    protected Battery battery;
    protected CpuAndRam cpuAndRam;
    protected Storage storage;
    protected Camera camera;
    protected Case safe;

    public abstract void prepare();

    public Phone(String name) {
        this.name = name;
    }

    public final void attachCpuAndRam() {
        System.out.println("\t\t>Attaching cpu & ram to the board: " + cpuAndRam);
    }

    public final void attachDisplay() {
        System.out.println("\t\t>Attaching display: " + display);
    }

    public final void attachBattery() {
        System.out.println("\t\t>Attaching battery: " + battery);
    }

    public final void attachStorage() {
        System.out.println("\t\t>Attaching storage: " + storage);
    }

    public final void attachCamera() {
        System.out.println("\t\t>Attaching camera: " + camera);
    }

    public final void encloseCase() {
        System.out.println("\t\t>Enclosing the phone case: " + safe);
    }

}
