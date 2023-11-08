package Humans;

import Diseases.Disease;
import Diseases.Healthy;
import Diseases.Undefined;
import Places.Hospital;
import Places.Place;

public class Neboska extends Human{
    public Neboska() {
        super("Небоська", new Undefined(), new Hospital("Больница"));
    }
}
