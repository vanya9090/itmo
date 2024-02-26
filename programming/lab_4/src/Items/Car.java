package Items;

import Exceptions.BrokenMotorException;
import Places.Place;

import java.util.Objects;

public class Car {
    private final String name;
    public Engine engine;
    public Car(String name, boolean isBroken){
        this.name = name;
        this.engine = new Engine(isBroken);
    }

    public void run() throws BrokenMotorException {
        if (this.engine.getIsBroken()){
            throw new BrokenMotorException("мотор сломан");
        }
        else {
            System.out.println("машина завыркала ");
            this.engine.work();
        }
    }

    public boolean getIsBroken(){
        return this.engine.getIsBroken();
    }
    public void drive(Place place){
        class Dust{
            public void raiseDust(){
                System.out.println("поднялась туча пыли");
            }
        }
        System.out.println("Колесить на месте: " + place.getName());
        if (Objects.equals(place.getName(), "Дом")){
            Dust dust = new Dust();
            dust.raiseDust();
        }
    }
    public class Engine {
        private final boolean isBroken;
        public Engine(boolean isBroken){
            this.isBroken = isBroken;
        }
        public void work(){
            System.out.println("мотор начал работать");
        }
        public boolean getIsBroken(){
            return this.isBroken;
        }
    }
}
