package Actions;

public abstract class Action {
    private final String name;
    private boolean isDone;
    public Action(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public abstract void run();

    public String getName() {
        return name;
    }

    public void setDone(boolean isDone){
        this.isDone = isDone;
    }

    public boolean getDone(){
        return this.isDone;
    }
}
