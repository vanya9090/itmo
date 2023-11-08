package Humans;

import Diseases.Undefined;
import Places.Hospital;

public class Siropchik extends Human{
    public Siropchik() {
        super("Сиропчик", new Undefined(), new Hospital("Больница"));
    }
}
