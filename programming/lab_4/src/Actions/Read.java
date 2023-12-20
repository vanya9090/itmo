package Actions;
import Humans.Human;
import Items.Readable;

public class Read extends Action{
    private final Readable list;
    public Read(String name, Readable list) {
        super(name, false);
        this.list = list;
    }

    @Override
    public void run(Human human) {
        this.list.getText();
        System.out.println(human.getName() + " стала просматривать больничный список");
        this.setDone(true);
    }
}
