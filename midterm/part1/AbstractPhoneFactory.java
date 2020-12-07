/**
 * AbstractPhoneFactory. Represent the one component of Abstract Factory Design
 * Pattern. There is a production routine followed here. Any other concrete
 * factories must derive AbstractPhoneFactory.
 * 
 * @see Phone.
 * @see PhoneFactory.
 */

public abstract class AbstractPhoneFactory {
    protected abstract Phone preparePhone(String model);

    public Phone createPhone(String model) {
        Phone phone;

        phone = preparePhone(model);

        phone.prepare();

        // production routine
        phone.attachBattery();
        phone.attachDisplay();
        phone.attachCpuAndRam();
        phone.attachStorage();
        phone.attachCamera();
        phone.encloseCase();

        return phone;
    }
}
