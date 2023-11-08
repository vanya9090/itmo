package Humans;

import Diseases.Disease;
import Items.Readable;
import Places.Place;

public class God extends Human implements Adult{
    public God(String name, Disease disease, Place location) {
        super(name, disease, location);
    }

    @Override
    public String read(Readable r) {
        return null;
    }

    @Override
    public void say() {
    }
}
