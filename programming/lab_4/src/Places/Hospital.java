package Places;

import Humans.Confines;
import Humans.Human;

public class Hospital extends Place{
    public Hospital(String name) {
        super(name);
    }

    public class Administration {
        private final String name;
        public Administration(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void discharge(Human human) {
            System.out.println(human.getName() + " выписан из " +  Hospital.this.getName());
            human.setLocation(Confines.FREEDOM);
        }
    }
}
