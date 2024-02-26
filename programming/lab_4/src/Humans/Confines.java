package Humans;

import Humans.Human;
import Places.Place;

import java.util.Objects;

public enum Confines {
    FREEDOM,
    CONFINED;

    @Override
    public String toString() {
        return "Confine: {"
                + "Name = " + this.name()
                + ", Hash = " + this.hashCode()
                + '}';
    }
}


