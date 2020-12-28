public class Driver {
    public static void main(String[] args) {

        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = (Singleton) singleton1.clone();
    }

}
