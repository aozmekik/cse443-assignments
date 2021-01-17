public abstract class Observer {
    protected SocietyController subject;

    public Observer(SocietyController subject)
    {
        this.subject = subject;
    }

    public abstract void update();
}
