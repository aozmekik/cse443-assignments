package Camera;

public class Camera3 extends Camera {

    public Camera3(String info) {
        super(info);
    }

    @Override
    public String spec() {
        return "Opt. zoom x3, " + toString();
    }
    
}
