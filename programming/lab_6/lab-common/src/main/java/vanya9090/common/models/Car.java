package vanya9090.common.models;

import java.io.Serializable;

public class Car implements Validatable, Serializable {
    private final Boolean cool; //Поле не может быть null
    private String name; //Поле не может быть null

    public Car(String name, Boolean cool) {
        this.cool = cool;
        this.name = name;
    }

    public Boolean getCool() {
        return this.cool;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean validate() {
        if (this.name == null) return false;
        if (this.name.isEmpty()) return false;
        return true;
    }
}
