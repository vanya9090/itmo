package Humans;

import Diseases.LegIll;
import Diseases.Undefined;
import Places.Hospital;

public class Pulka extends Human implements Endured{
    public Pulka() {
        super("Пулька", new LegIll(5), new Hospital("Больница"));
    }

    @Override
    public String endure() {
        return this.getName() + " молча терпел несправедливость";
    }
}
