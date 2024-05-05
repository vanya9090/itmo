package vanya9090.common.models;

import java.io.Serializable;
import java.util.Map;

public class Car implements Validatable, Serializable {
    private final Boolean cool; //Поле не может быть null
    private String name; //Поле не может быть null

    public Car(String name, Boolean cool) {
        this.cool = cool;
        this.name = name;
    }

    public Car(Map<String, Object> carMap) {
        this.cool = (Boolean) carMap.get("cool");
        this.name = (String) carMap.get("name");
    }

    public Boolean getCool() {
        return this.cool;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean validate() {
        if (this.name == null) return false;
        if (this.name.isEmpty()) return false;
        return true;
    }
}
