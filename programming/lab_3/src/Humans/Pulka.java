package Humans;

import Diseases.LegIll;
import Diseases.Undefined;
import Places.Hospital;

public class Pulka extends Human implements Endured{
    public Pulka() {
        super("Пулька", new LegIll(5), new Hospital("Больница"));
    }

    @Override
    public void endure() {
        System.out.println("Персонаж " + this.getName() + " терпит несправедливость");
    }
}
