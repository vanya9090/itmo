package Actions;

import Exceptions.HumanNotDoctorException;
import Exceptions.TOOMUCHANGRY;
import Humans.Human;
import Humans.Roles;

public class Hear extends Action {
    private final String message;

    public Hear(String name, String message) {
        super(name, false);
        this.message = message;
    }

    @Override
    public void run(Human performingHuman){
        System.out.println("сообщение услышано: " + message);
        this.setDone(true);
        if (message.contains("выпишите")){
            if (performingHuman.getRole() != Roles.DOCTOR){
                throw new HumanNotDoctorException("Человек не доктор");
            }
            throw new TOOMUCHANGRY("выписать непослушных", performingHuman);
        }
    }
}
