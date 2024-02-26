package Health;

import Actions.Endure;
import Actions.Action;
import Humans.Human;

public class LegDisease extends Health implements IPhysicalPain{
    public LegDisease(String name) {
        super(name);
    }

    @Override
    public void setLimits(Human human){
        System.out.print("Нога у " + human.getName() + " все еще болит, поэтому он ");
        Action endureInjustice = new Endure( "молча терпит несправедливость");
        human.applyAction(endureInjustice);
    }
}
