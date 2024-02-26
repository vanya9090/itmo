package Places;

import Confines.Confines;
import Humans.Human;

public class Hospital extends Place{

    public Hospital(String name) {
        super(name);
    }

    public void Discharge(Human human){
        System.out.println(human.getName() + " выписан из " + this.getName());
        human.setLocation(Confines.FREEDOM);
    }
}
