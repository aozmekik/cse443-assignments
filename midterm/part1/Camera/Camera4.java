package Camera;

public class Camera4 extends Camera {

    public Camera4(String info) {
        super(info);
    }

    @Override
    public String spec() {
        return "Opt. zoom x4, " + toString();
    }
    
}
