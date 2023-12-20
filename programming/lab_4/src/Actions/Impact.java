package Actions;

import Exceptions.BrokenLegException;
import Humans.Human;
import Places.Hospital;

public class Impact extends Action{
    private final Human[] humans;
    private final Hospital.Administration administration;
    public Impact(String name, Hospital.Administration administration, Human[] humans) {
        super(name, false);
        this.humans = humans;
        this.administration = administration;
    }

    @Override
    public void run(Human performingHuman) {
        System.out.println(performingHuman.getName() + " добилась от" + administration.getName() +  ":");
        for (Human human: humans){
            this.administration.discharge(human);
        }
        this.setDone(true);
    }
}
