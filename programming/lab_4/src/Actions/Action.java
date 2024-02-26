package Actions;

import Exceptions.BrokenLegException;
import Exceptions.TOOMUCHANGRY;
import Humans.Human;
import Places.Place;
import Time.*;

import java.util.Objects;

public abstract class Action {
    private final String name;
    private boolean isDone;
    public Action(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public abstract void run(Human human);

    public String getName() {
        return name;
    }

    public void setDone(boolean isDone){
        this.isDone = isDone;
    }

    public boolean getDone(){
        return this.isDone;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this){
            return true;
        }
        Action anotherAction = (Action) o;
        return (Objects.equals(this.name, anotherAction.name) & (this.getDone() == anotherAction.getDone()));
    }

    @Override
    public String toString() {
        return "Place: {"
                + "Name = " + this.getName() + '\''
                + ", isDone = " + this.getDone() + '\''
                + ", Hash = " + this.hashCode()
                + '}';
    }
}