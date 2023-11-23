package Actions;

public class thinkAnnoyance extends Action implements IThinks{

    public thinkAnnoyance(String name) {
        super(name, false);
    }

    @Override
    public void run() {
        this.think();
        this.setDone(true);
    }

    @Override
    public void think() {
        System.out.println(" готов рвать на себе волосы от досады");
    }
}
