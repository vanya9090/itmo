package vanya9090.common.models;

import java.io.Serializable;
import java.util.Map;

public class Coordinates implements Validatable, Serializable {
    private final Integer x; //Максимальное значение поля: 925, Поле не может быть null
    private final Float y; //Значение поля должно быть больше -208, Поле не может быть null

    public Coordinates(Integer x, Float y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(Map<String, Object> coordinatesMap) {
        this.x = (Integer) coordinatesMap.get("x");
        this.y = (Float) coordinatesMap.get("y");
    }

    public double getDistance() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y;
    }

    @Override
    public boolean validate() {
        if (this.x == null || this.y == null) return false;
        if (this.x > 925) return false;
        if (this.y <= -208) return false;
        return true;
    }
}
