import Battery.Battery;
import Camera.Camera;
import Case.Case;
import CpuAndRam.CpuAndRam;
import Display.Display;
import Storage.Storage;

/**
 * PhoneIngredientFactory. Provides and interface for those who wants to become
 * a ingredient factory. 
 */

public interface PhoneIngredientFactory {
    public Display createDisplay(String inch);

    public Battery createBattery(String info);

    public CpuAndRam createCpuAndRam(String info);

    public Storage createStorage(String info);

    public Camera createCamera(String info);

    public Case createCase(String info);
}
