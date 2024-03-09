package com.vanya9090.client.managers;

import com.vanya9090.client.exceptions.WrongFieldsException;
import com.vanya9090.client.models.HumanBeing;

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

    public void validate() throws WrongFieldsException {
        for (HumanBeing humanBeing : this.collection) {
            System.out.println(humanBeing.validate());
            if (!humanBeing.validate()) throw new WrongFieldsException(humanBeing.getId());
        }
    }

    public void readCollection(String envKey) throws WrongFieldsException {
        this.collection = (ArrayDeque<HumanBeing>) jsonManager.readFile(envKey);
        this.initDate = LocalDateTime.now();
        this.validate();
    }

    public void writeCollection() {

    }
}
