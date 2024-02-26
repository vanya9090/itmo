package Actions;

import Exceptions.TOOMUCHANGRY;
import Humans.Human;
import Time.Time;

public class WakeUp extends Action{
    private Time time;
    public WakeUp(String name, Time time) {
        super(name, false);
        this.time = time;
    }

    @Override
    public void run(Human human) throws TOOMUCHANGRY {
        if (4 < time.getTime() & time.getTime() < 7){
            System.out.println(human.getName() + " проснулся ни свет ни заря");
        }else {
            System.out.println(human.getName() + " проснулся поздно");
        }
        this.setDone(true);
    }
}
