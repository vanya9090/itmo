package vanya9090.client.managers;


import com.google.gson.JsonSyntaxException;
import vanya9090.client.models.HumanBeing;
import vanya9090.common.exceptions.AccessException;
import vanya9090.common.exceptions.EmptyFileException;
import vanya9090.common.exceptions.NotFoundException;
import vanya9090.common.exceptions.ValidateException;

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
            if (element.getId() == id) return element;
        }
        return null;
    }

    public void readCollection(String ENV_KEY) throws JsonSyntaxException, EmptyFileException, ValidateException, AccessException, NotFoundException {
        this.collection = (ArrayDeque<HumanBeing>) jsonManager.readFile(ENV_KEY);
        this.initDate = LocalDateTime.now();
    }

    public void writeCollection() {

    }
}
