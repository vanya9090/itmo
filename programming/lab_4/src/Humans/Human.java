package Humans;

import Actions.Action;
import Exceptions.BrokenLegException;
import Health.Health;

import java.util.Objects;

public class Human {
    private final String name;
    private Health health;
    private Confines location;
    private Roles role;

    public Human(String name, Health health, Confines location, Roles role) {
        this.name = name;
        this.health = health;
        this.location = location;
        this.role = role;
        System.out.println("Создан персонаж " + this.name);
    }

    public String getName() {
        return this.name;
    }
    public Confines getLocation() {
        return this.location;
    }

    public void setLocation(Confines location) {
        if (Objects.equals(this.getHealth().getName(), "Боль в ноге")){
            throw new BrokenLegException("боль в ноге у " + this.getName());
        }
        else {
            this.location = location;
        }
    }

    public Health getHealth() {
        return this.health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public void applyAction(Action action){
        action.run(this);
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