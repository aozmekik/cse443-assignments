import Battery.Battery;
import Battery.BatteryCobalt;
import Camera.Camera;
import Camera.Camera2;
import Case.Case;
import Case.Case50;
import CpuAndRam.CpuAndRam;
import CpuAndRam.CpuAndRam2;
import Display.Display;
import Display.Display24;
import Storage.Storage;
import Storage.Storage32;

public class GlobalPhoneIngredientFactory implements PhoneIngredientFactory {

    @Override
    public Display createDisplay(String inch) {
        return new Display24(inch);
    }

    @Override
    public Battery createBattery(String info) {
        return new BatteryCobalt(info);
    }

    @Override
    public CpuAndRam createCpuAndRam(String info) {
        return new CpuAndRam2(info);
    }

    @Override
    public Storage createStorage(String info) {
        return new Storage32(info);
    }

    @Override
    public Camera createCamera(String info) {
        return new Camera2(info);
    }

    @Override
    public Case createCase(String info) {
        return new Case50(info);
    }

}
