package com.vanya9090.client.models;

public class Car {
    private final Boolean cool; //Поле не может быть null
    private final String name; //Поле не может быть null

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
}
