package vanya9090.server.managers;

import vanya9090.server.models.HumanBeing;

import java.time.LocalDateTime;
import java.util.ArrayDeque;

public class CollectionManager extends ArrayDequeManager<HumanBeing>{
    private LocalDateTime initDate;
    public CollectionManager() {
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
    public void updateCollection(ArrayDeque<HumanBeing> collection) {
        this.collection = collection;
        this.initDate = LocalDateTime.now();
    }
}
