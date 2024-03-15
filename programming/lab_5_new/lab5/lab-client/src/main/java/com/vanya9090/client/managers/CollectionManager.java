package com.vanya9090.client.managers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.vanya9090.client.exceptions.*;
import com.vanya9090.client.handlers.*;
import com.vanya9090.client.models.*;
import com.vanya9090.client.validators.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Deque;

public class CollectionManager {
    private final JSONManager jsonManager;
    private Deque<HumanBeing> collection = new ArrayDeque<HumanBeing>();
    private LocalDateTime initDate;

    public CollectionManager(JSONManager jsonManager) {
        this.jsonManager = jsonManager;
    }

    public Deque<HumanBeing> getCollection() {
        return this.collection;
    }

    public int getSize() {
        return this.collection.size();
    }

    public String getType() {
        return this.collection.getClass().getName();
    }

    public void add(HumanBeing humanBeing) {
        this.collection.add(humanBeing);
    }

    public void remove(HumanBeing humanBeing) {
        this.collection.remove(humanBeing);
    }

    public void removeFirst() {
        this.collection.remove();
    }

    public HumanBeing removeHead() {
        return this.collection.poll();
    }

    public void clear() {
        this.collection.clear();
    }

    public LocalDateTime getInitDate() {
        return this.initDate;
    }

    public HumanBeing getById(int id) {
        for (HumanBeing element : collection) {
            if (element.getId() == id) {
                return element;
            }
        }
        return null;
    }

    public void readCollection(JsonArray jsonElements) throws WrongFieldsException, ReadException, WrongPathException, EmptyFieldException, ParseException, NullFieldException {

        for (JsonElement jsonElement: jsonElements) {
            try {
                String fieldId = jsonElement.getAsJsonObject().get("id").getAsString();
                id = intHandler.handle(fieldId, "id");
                if (!idValidator.validate(id)) throw new WrongFieldsException(0, "id");
            } catch (UnsupportedOperationException e) {
                throw new NullFieldException("id");
            }



            Coordinates coordinates = new Coordinates(x, y);
            Car car = new Car(nameCar, coolCar);
            HumanBeing humanBeing = new HumanBeing(id, name, coordinates, creationDate, realHero, hasToothpick, impactSpeed, minutesOfWaiting, weaponType, mood, car);
            humanBeing.validate();
            this.add(humanBeing);
        }
        this.initDate = LocalDateTime.now();
    }
}
