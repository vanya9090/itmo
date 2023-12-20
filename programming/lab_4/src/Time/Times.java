package Time;

public enum Times {
    MORNING(3),
    EVENING(21);

    private int time;
    Times(int time){
        this.time = time;
    }

    public int getTime() {
        return time;
    }
}
