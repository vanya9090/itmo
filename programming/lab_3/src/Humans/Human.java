package Humans;

import Actions.Action;
import Health.Health;
import Confines.Confines;
import Places.Place;

import java.util.Objects;

public class Human {
    private final String name;
    private Health health;
    private Confines location;
    public Human(String name, Health health, Confines location) {
        this.name = name;
        this.health = health;
        this.location = location;
        System.out.println("Создан персонаж " + this.name);
    }

    public String getName() {
        return this.name;
    }
    public Confines getLocation() {
        return this.location;
    }

    public void setLocation(Confines location) {
//        System.out.println("Местоположение " + this.getName() + " изменено на " + location.toString());
        this.location = location;
    }

    public Health getHealth() {
        return this.health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public void applyAction(Action action){
        action.run();
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + this.location.hashCode() + this.health.hashCode();
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
                + "Name = " + this.getName() + '\''
                + ", Health = " + this.getHealth() + '\''
                + ", Location = " + this.getLocation() + '\''
                + ", Hash = " + this.hashCode()
                + '}';
    }
}