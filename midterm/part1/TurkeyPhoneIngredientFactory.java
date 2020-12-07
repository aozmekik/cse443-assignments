import Battery.Battery;
import Battery.BatteryBorron;
import Camera.Camera;
import Camera.Camera4;
import Case.Case;
import Case.Case2;
import CpuAndRam.CpuAndRam;
import CpuAndRam.CpuAndRam8;
import Display.Display;
import Display.Display32;
import Storage.Storage;
import Storage.Storage128;

/**
 * TurkeyPhoneIngredientFactory. Represents an ingredient factory which creates
 * different kind of ingredients by implementing the PhoneIngredientFactory
 * interface.
 * 
 * @see PhoneIngredientFactory.
 */

public class TurkeyPhoneIngredientFactory implements PhoneIngredientFactory {

    @Override
    public Display createDisplay(String inch) {
        return new Display32(inch);
    }

    @Override
    public Battery createBattery(String info) {
        return new BatteryBorron(info);
    }

    @Override
    public CpuAndRam createCpuAndRam(String info) {
        return new CpuAndRam8(info);
    }

    @Override
    public Storage createStorage(String info) {
        return new Storage128(info);
    }

    @Override
    public Camera createCamera(String info) {
        return new Camera4(info);
    }

    @Override
    public Case createCase(String info) {
        return new Case2(info);
    }

}
