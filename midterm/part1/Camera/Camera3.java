package Camera;

/**
 * Camera2. Camera with optical zoom x3. Represents the one ingredient of Phone.
 * 
 * @see Phone.
 */

public class Camera3 extends Camera {

    public Camera3(String info) {
        super(info);
    }

    @Override
    public String toString() {
        return "Opt. zoom x3, " + super.toString();
    }

}
