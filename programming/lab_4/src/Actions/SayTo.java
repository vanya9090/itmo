package Actions;

import Exceptions.TOOMUCHANGRY;
import Humans.Human;

public class SayTo extends Action{
    private final Human human;
    private final String message;
    private boolean isHeared = false;
    public SayTo(String name, String message, Human human) {
        super(name, false);
        this.human = human;
        this.message = message;
    }

    public void setIsHeared(boolean isHeared){
        this.isHeared = isHeared;
    }

    @Override
    public void run(Human human) {
        System.out.println("сообщение сказано: " + this.message);
        Hear hear = new Hear("услышать сообщение", this.message);

        try {
            this.human.applyAction(hear);
        }
        catch (TOOMUCHANGRY exception){
            System.out.println("не все будут выпущены из больницы");
        }

        if (hear.getDone()){
            this.setIsHeared(true);
        }
        this.setDone(true);
    }
}
