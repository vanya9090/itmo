package vanya9090.client.managers;


import vanya9090.client.models.HumanBeing;
import vanya9090.common.exceptions.*;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * менеджер коллекции
 *
 * @author vanya9090
 */
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

    public void readCollection(String ENV_KEY) throws EmptyFileException, ValidateException, AccessException, NotFoundException, FormatException {
        this.collection = (ArrayDeque<HumanBeing>) jsonManager.readFile(ENV_KEY);
        this.initDate = LocalDateTime.now();
    }

    public void writeCollection(String ENV_KEY) throws AccessException, NotFoundException {
        jsonManager.writeFile(this.getCollection(), ENV_KEY);
    }
}
