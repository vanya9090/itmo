package Humans;

import Diseases.Undefined;
import Items.Readable;
import Places.Hospital;

public class Pilulkin extends Human implements Angry, Adult{
    public Pilulkin() {
        super("Пилюлькин", new Undefined(), new Hospital("Больница"));
    }

    @Override
    public String tearingHair() {
        return "рвать волосы";
    }

    @Override
    public String read(Readable r) {
        return null;
    }

    @Override
    public String say() {
        return "что если к вечеру их не выпишут, то они устроят побег.";
    }
}