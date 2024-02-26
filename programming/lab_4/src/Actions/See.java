package Actions;

import Exceptions.TOOMUCHANGRY;
import Humans.Human;

public class See extends Action{
    private Human[] humans;
    public See(String name, Human[] humans) {
        super(name, false);
        this.humans = humans;
    }

    @Override
    public void run(Human human) {
        System.out.println(human.getName() + " увидел малышек, которые занимались уборкой фруктов.");
        this.setDone(true);
    }
}
