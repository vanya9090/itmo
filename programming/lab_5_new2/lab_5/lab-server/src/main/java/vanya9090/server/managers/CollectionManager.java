package vanya9090.server.managers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import vanya9090.common.Validators.IdValidator;
import vanya9090.common.Validators.Validator;
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

    public void readCollection(JsonArray jsonElements) {
        for (JsonElement jsonElement: jsonElements) {
            Validator<Integer> idValidator = new IdValidator();
            String field = jsonElement.getAsJsonObject().get("id").getAsString();
            if (idValidator.validate(field)) {
                int id = idValidator.handle(field);
            }
            String name = jsonElement.getAsJsonObject().get("name").getAsString();
            String x = jsonElement.getAsJsonObject().get("coordinates").getAsJsonObject().get("x").getAsString();
            String y = jsonElement.getAsJsonObject().get("coordinates").getAsJsonObject().get("x").getAsString();
            String creationDate = jsonElement.getAsJsonObject().get("creationDate").getAsString();
            String realHero = jsonElement.getAsJsonObject().get("realHero").getAsString();
            String hasToothpick = jsonElement.getAsJsonObject().get("hasToothpick").getAsString();
            String impactSpeed = jsonElement.getAsJsonObject().get("impactSpeed").getAsString();
            String minutesOfWaiting = jsonElement.getAsJsonObject().get("minutesOfWaiting").getAsString();
            String weaponType = jsonElement.getAsJsonObject().get("weaponType").getAsString();
            String mood = jsonElement.getAsJsonObject().get("mood").getAsString();
            String nameCar = jsonElement.getAsJsonObject().get("car").getAsJsonObject().get("name").getAsString();
            String collCar = jsonElement.getAsJsonObject().get("car").getAsJsonObject().get("cool").getAsString();
        }
    }
}
