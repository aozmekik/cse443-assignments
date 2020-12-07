package Camera;

/**
 * Camera2. Camera with optical zoom x4. Represents the one ingredient of Phone.
 * 
 * @see Phone.
 */

public class Camera4 extends Camera {

    public Camera4(String info) {
        super(info);
    }

    @Override
    public String toString() {
        return "Opt. zoom x4, " + super.toString();
    }

}
