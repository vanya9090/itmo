package Places;

import java.util.Objects;

public class Place {
    private final String name;
    public Place(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this){
            return true;
        }
        Place anotherPlace = (Place) o;
        return Objects.equals(this.name, anotherPlace.name);
    }

    @Override
    public String toString() {
        return "Place: {"
                + ", Name = " + this.getName() + '\''
                + ", Hash = " + this.hashCode()
                + '}';
    }
}