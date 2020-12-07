package Camera;

/**
 * Camera. Represents the one ingredient of Phone.
 * 
 * @see Phone.
 */

public abstract class Camera {
    private String info;

    public Camera(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return info;
    }

}
