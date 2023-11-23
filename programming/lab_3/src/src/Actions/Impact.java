package Actions;

import Humans.Human;
import Confines.Confines;
import Places.Hospital;
import Places.Place;

public class Impact extends Action{
    private final Human[] humans;
    private final Hospital hospital;
    public Impact(String name, Hospital hospital, Human[] humans) {
        super(name, false);
        this.humans = humans;
        this.hospital = hospital;
    }

    @Override
    public void run(){
        for (Human human: humans){
            this.hospital.Discharge(human);
        }
        this.setDone(true);
    }
}
