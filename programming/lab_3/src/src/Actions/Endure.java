package Actions;

public class Endure extends Action implements IThinks{

    public Endure(String name) {
        super(name, false);
    }

    @Override
    public void run() {
        this.think();
        this.setDone(true);
    }

    @Override
    public void think() {
        System.out.println("сказал, что устроит побег");
    }
}
