package Actions;

import Humans.Human;

public class Picking extends Action{
    public Picking(String name) {
        super(name, false);
    }

    @Override
    public void run(Human human) {
        System.out.println(human.getName() + " занимается уборкаой фруктов");
    }
}
