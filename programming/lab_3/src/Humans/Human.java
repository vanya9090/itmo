package Humans;

import Diseases.Disease;
import Places.Place;

import java.util.Objects;

public abstract class Human {
    private String name;
    private Disease disease;
    private Place location;
    public Human(String name, Disease disease, Place location){
        this.name = name;
        this.disease = disease;
        this.location = location;
        System.out.println("Создан персонаж " + this.name);
    }
    public String getName() {
        return this.name;
    }
    public Place getLocation() {
        return this.location;
    }

    public void setLocation(Place location) {
        System.out.println("Местоположение " + this.getName() + " изменено на " + location.getName());
        this.location = location;
    }

    public Disease getDisease() {
        return this.disease;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + this.disease.hashCode() + this.location.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this){
            return true;
        }
        Human anotherHuman = (Human) o;
        return Objects.equals(this.name, anotherHuman.name);
    }

    @Override
    public String toString() {
        return "Human: {"
                + "Name = '" + this.getName() + '\''
                + "Location = " + this.getLocation() + '\''
                + "Disease = " + this.getDisease() + '\''
                + "Hash = " + this.hashCode()
                + '}';
    }
}
