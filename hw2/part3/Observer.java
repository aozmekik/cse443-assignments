public abstract class Observer {
    protected HiTech subject;

    public abstract void update(int timeout);
}