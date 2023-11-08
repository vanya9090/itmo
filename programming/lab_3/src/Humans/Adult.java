package Humans;

import Diseases.Disease;
import Items.Readable;
import Places.Place;

public interface Adult {
    public String read(Readable r);
    public void say();
}
