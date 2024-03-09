package vanya9090.server.managers;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class ArrayDequeManager<E> {
    protected Deque<E> collection = new ArrayDeque<E>();
    public Deque<E> getCollection() {
        return this.collection;
    }
    public int getSize() {
        return this.collection.size();
    }
    public String getType() {
        return this.collection.getClass().getName();
    }
    public void remove(E e) {
        this.collection.remove(e);
    }
    public void removeFirst() {
        this.collection.remove();
    }
    public E removeHead() {
        return this.collection.poll();
    }
    public void clear() {
        this.collection.clear();
    }
}
