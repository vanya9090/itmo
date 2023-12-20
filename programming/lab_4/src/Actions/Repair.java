package Actions;

import Exceptions.TOOMUCHANGRY;
import Humans.Human;
import Items.Car;

import java.lang.reflect.Field;

public class Repair extends Action{
    private final Car car;
    public Repair(String name, Car car) {
        super(name, false);
        this.car = car;
    }

    @Override
    public void run(Human human) {
        System.out.println(human.getName() + " чинит автомобиль");

        Class<? extends Car.Engine> engineClass = this.car.engine.getClass();
        Field[] fields = engineClass.getDeclaredFields();
        for (Field field: fields){
            if (field.getType().getName().equals("boolean")){
                field.setAccessible(true);
                try {
                    field.set(this.car.engine, false);
                } catch (IllegalAccessException e){
                    System.out.println("error");
                }
            }
        }

        this.setDone(true);
//        car.setIsBroken(false);
    }
}
