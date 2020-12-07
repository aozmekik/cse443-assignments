package Camera;

/**
 * Camera2. Camera with optical zoom x2. Represents the one ingredient of Phone.
 * 
 * @see Phone.
 */

public class Camera2 extends Camera {

    public Camera2(String info) {
        super(info);
    }

    @Override
    public String toString() {
        return "Opt. zoom x2, " + super.toString();
    }

}
