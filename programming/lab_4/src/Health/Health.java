package Health;

import java.util.Objects;

public class Health {
    private final String name;
    Health(String name){
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
        Health anotherDisease = (Health) o;
        return Objects.equals(this.name, anotherDisease.name);
    }

    @Override
    public String toString() {
        return "Health: {"
                + "Name = " + this.getName()
                + ", Hash = " + this.hashCode()
                + '}';
    }
}
