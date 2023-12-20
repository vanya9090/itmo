package Actions;

import Humans.Human;

public class Endure extends Action{

    public Endure(String name) {
        super(name, false);
    }

    @Override
    public void run(Human human) {
        System.out.println(this.getName());
        this.setDone(true);
    }
}
