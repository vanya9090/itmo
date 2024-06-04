package vanya9090.server.managers;


import vanya9090.common.models.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 * менеджер коллекции
 * @author vanya9090
 */
public class CollectionManager {
    private Deque<HumanBeing> collection = new ArrayDeque<>();
    private LocalDateTime initDate;
    private StorageManager storageManager;


    public CollectionManager(StorageManager storageManager) {
        this.storageManager = storageManager;
    }

    public Deque<HumanBeing> getCollection() {
        return this.collection;
    }

//    public Map<Integer, String> getUserCollection() throws Exception {
//        Map<Integer, String> res = new HashMap<>();
//        for (HumanBeing humanBeing : this.collection) {
//            res.put(humanBeing.getId(), this.storageManager.getUserByHumanId(humanBeing.getId()));
//        }
//        return res;
//    }

    public Map<Integer, String> getUserCollection() throws Exception {
        return this.storageManager.getUsersHumans();
    }

    public int getSize() {
        return this.collection.size();
    }

    public String getType() {
        return this.collection.getClass().getName();
    }

    public void add(HumanBeing humanBeing, User user) throws Exception {
        System.out.println(humanBeing);
        this.storageManager.add(humanBeing, user);
        this.collection.add(humanBeing);
    }

    public void update(HumanBeing humanBeing, int id, User user) throws Exception {
        HumanBeing humanToUpdate = this.getById(id);
        this.storageManager.update(humanBeing, id, user);
        humanToUpdate.update(humanBeing);
    }

    public void remove(HumanBeing humanBeing, User user) throws Exception {
        this.storageManager.remove(humanBeing.getId(), user);
        this.collection.remove(humanBeing);
    }

    public void removeFirst(User user) throws Exception {
        remove(this.collection.getFirst(), user);
    }

    public HumanBeing removeHead(User user) throws Exception {
        HumanBeing humanBeing = collection.getFirst();
        remove(collection.getFirst(), user);
        return humanBeing;
    }

    public void clear(User user) throws Exception {
        this.storageManager.deleteByUser(user);
        this.setCollection(this.storageManager.read());
//        this.collection.clear();
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

    public int getNextId() {
        int maxId = this.getCollection()
            .stream().filter(Objects::nonNull)
            .map(HumanBeing::getId)
            .mapToInt(Integer::intValue).max().orElse(0);
        return maxId + 1;
    }

    public void setCollection(Deque<HumanBeing> collection) throws Exception {
        this.collection = collection;
        this.initDate = LocalDateTime.now();
    }

//    public void writeCollection() throws Exception {
//        storageManager.write(this.collection);
//    }
}
