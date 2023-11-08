package Humans;

import Diseases.Disease;
import Diseases.Healthy;
import Items.Readable;
import Places.Freedom;
import Places.Place;

public class Meduniza extends Human implements Adult{
    public Meduniza() {
        super("Медуница", new Healthy(), new Freedom());
    }

    @Override
    public String read(Readable r) {
        String text = r.getText(this);
        return this.getName() + " снова стала просматривать " + r.getName() + ".";
    }

    @Override
    public String say() {
        return null;
    }
}
