package Places;


import java.util.Objects;

public abstract class Place {
    private PlaceType type;
    private String name;
    public Place(PlaceType type, String name){
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public PlaceType getType() {
        return type;
    }

    @Override
    public int hashCode() {
        return this.type.hashCode() + this.name.hashCode();
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
                + "Type = '" + this.getType() + '\''
                + "Name = " + this.getName() + '\''
                + "Hash = " + this.hashCode()
                + '}';
    }
}
