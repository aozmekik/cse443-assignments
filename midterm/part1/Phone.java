import Battery.Battery;
import Camera.Camera;
import Case.Case;
import CpuAndRam.CpuAndRam;
import Display.Display;
import Storage.Storage;

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
        System.out.println("\tAttaching cpu & ram to the board: " + cpuAndRam);
    }

    public final void attachDisplay() {
        System.out.println("\tAttaching display: " + display);
    }

    public final void attachBattery() {
        System.out.println("\tAttaching battery: " + battery);
    }

    public final void attachStorage() {
        System.out.println("\tAttaching storage: " + storage);
    }

    public final void attachCamera() {
        System.out.println("\tAttaching camera: " + camera);
    }

    public final void encloseCase() {
        System.out.println("\tEnclosing the phone case: " + safe);
    }

}
