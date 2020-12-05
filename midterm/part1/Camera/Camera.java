package Camera;

public abstract class Camera {
    private String info;

    public Camera(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return info;
    }

    public abstract String spec();

}
