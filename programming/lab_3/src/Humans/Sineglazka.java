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
        System.out.println(this.getName() + " добилась того, что " + human.getName() + " выписали из " + hospital.getName());
        return hospital.Discharge(human);
    }
}
