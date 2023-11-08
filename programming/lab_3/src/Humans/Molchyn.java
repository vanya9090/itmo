package Humans;

import Diseases.Undefined;
import Places.Hospital;

public class Molchyn extends Human{
    public Molchyn() {
        super("Молчун", new Undefined(), new Hospital("Больница"));
    }
}
