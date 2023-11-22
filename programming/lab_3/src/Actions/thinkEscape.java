package Actions;

public class thinkEscape extends Action implements IThinks{
    public thinkEscape(String name) {
        super(name, false);
    }

    @Override
    public void run() {
        this.think();
        this.setDone(true);
    }

    @Override
    public void think() {
        System.out.println(" сказал, что если к вечеру его не выпишут, то он устроит побег");
    }
}
