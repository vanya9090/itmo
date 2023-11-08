package Humans;

import Diseases.Undefined;
import Places.Hospital;

public class Ponchik extends Human{
    public Ponchik() {
        super("Пончик", new Undefined(), new Hospital("Больница"));
    }
}
