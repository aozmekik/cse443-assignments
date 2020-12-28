public interface State {
    public void toGreen();

    public void toRed();

    public void toYellow();

    public void setTimeout(int timeout);
}