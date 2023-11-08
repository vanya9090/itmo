package Humans;

import Diseases.Undefined;
import Items.Readable;
import Places.Hospital;

public class Vorchyn extends Human implements Angry, Adult{
    public Vorchyn() {
        super("Ворчун", new Undefined(), new Hospital("Больница"));
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