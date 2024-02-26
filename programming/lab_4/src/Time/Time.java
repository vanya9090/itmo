package Time;

import java.util.TimeZone;

public class Time {
    private String timeCode;
    private int bias;
    static private int time;
    public Time(String timeCode){
        this.timeCode = timeCode;
        this.bias = Integer.parseInt(this.timeCode.replaceAll("[^0-9]", ""));
    }

    public int getTime() {
        return time + this.bias;
    }

    @Override
    public String toString() {
        return "Time{" +
                "timeCode='" + this.timeCode + '\'' +
                ", time=" + this.getTime() +
                '}';
    }

    public static class TimeUtils{
        public void setTime(int timeArg){
            time = timeArg;
        }
        public void addTime(int timeArg){
            time += timeArg;
        }
    }
}
