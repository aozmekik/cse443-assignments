import Battery.Battery;
import Battery.BatteryIon;
import Camera.Camera;
import Camera.Camera3;
import Case.Case;
import Case.Case1;
import CpuAndRam.CpuAndRam;
import CpuAndRam.CpuAndRam4;
import Display.Display;
import Display.Display24;
import Storage.Storage;
import Storage.Storage64;

public class EUPhoneIngredientFactory implements PhoneIngredientFactory {

    @Override
    public Display createDisplay(String inch) {
        return new Display24(inch);
    }

    @Override
    public Battery createBattery(String info) {
        return new BatteryIon(info);
    }

    @Override
    public CpuAndRam createCpuAndRam(String info) {
        return new CpuAndRam4(info);
    }

    @Override
    public Storage createStorage(String info) {
        return new Storage64(info);
    }

    @Override
    public Camera createCamera(String info) {
        return new Camera3(info);
    }

    @Override
    public Case createCase(String info) {
        return new Case1(info);
    }

}
