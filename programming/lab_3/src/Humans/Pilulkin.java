package Humans;

import Diseases.Undefined;
import Items.Readable;
import Places.Hospital;

public class Pilulkin extends Human implements Angry, Adult{
    public Pilulkin() {
        super("Пилюлькин", new Undefined(), new Hospital("Больница"));
    }

    @Override
    public void tearingHair() {
        System.out.println("Персонаж " + this.getName() + " готов рвать волосы");
    }

    @Override
    public String read(Readable r) {
        return null;
    }

    @Override
    public void say() {
        System.out.println("Персонаж " + this.getName() + " сказал, что если к вечеру его не выпишут, то он устроит побег");
    }
}