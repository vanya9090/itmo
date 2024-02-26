package Actions;

import Exceptions.TOOMUCHANGRY;
import Humans.Human;

public class Say extends Action{
    private final String message;
    public Say(String name, String message) {
        super(name, false);
        this.message = message;
    }

    @Override
    public void run(Human human) throws TOOMUCHANGRY {
        System.out.println(human.getName() + " сказала:");
        System.out.println(message);
    }
}
