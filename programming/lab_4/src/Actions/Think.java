package Actions;

import Humans.Human;

public class Think extends Action{
    private final String thought;
    public Think(String name, String thought){
        super(name, false);
        this.thought = thought;
    }
    @Override
    public void run(Human performingHuman) {
        System.out.println(performingHuman.getName() + " " + thought);
    }
}
