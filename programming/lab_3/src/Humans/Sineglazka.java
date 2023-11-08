package Humans;

import Diseases.Disease;
import Diseases.Healthy;
import Places.Freedom;
import Places.Hospital;
import Places.Place;

public class Sineglazka extends Human implements AbleToImpact{

    public Sineglazka() {
        super("Синеглазка", new Healthy(), new Freedom());
    }

    @Override
    public String impact(Hospital hospital, Human human) {
        return hospital.Discharge(human);
    }
}
