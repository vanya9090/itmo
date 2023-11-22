package Actions;
import Items.Readable;

public class Read extends Action{
    private final Readable list;
    public Read(String name, Readable list) {
        super(name, false);
        this.list = list;
    }

    @Override
    public void run() {
        System.out.println(this.getName());
        this.setDone(true);
    }
}
