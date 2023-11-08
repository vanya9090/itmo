package Places;

import Humans.Human;

public class Hospital extends Place{

    public Hospital(String name) {
        super(PlaceType.HOSPITAL, name);
    }

    public String Discharge(Human h){
        h.setLocation(new Freedom());
        return h.getName() + " выписан из " + this.getName();
    }
}
