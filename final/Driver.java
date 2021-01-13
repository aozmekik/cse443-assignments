import java.awt.EventQueue;

class Driver {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SocietySimulator();
            }
        });
    }
}