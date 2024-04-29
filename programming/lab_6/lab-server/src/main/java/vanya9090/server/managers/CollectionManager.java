package vanya9090.server.managers;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import vanya9090.common.handlers.*;
import vanya9090.common.models.*;
import vanya9090.common.validators.*;
import vanya9090.common.exceptions.*;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * менеджер коллекции
 * @author vanya9090
 */
public class CollectionManager {
    private final JSONManager jsonManager;
    private Deque<HumanBeing> collection = new ArrayDeque<>();
    private LocalDateTime initDate;

    public CollectionManager(JSONManager jsonManager) {
        this.jsonManager = jsonManager;
    }

    public Deque<HumanBeing> getCollection() {
        return this.collection;
    }

    public int getSize() {
        return this.collection.size();
    }

    public String getType() {
        return this.collection.getClass().getName();
    }

    public void add(HumanBeing humanBeing) {
        this.collection.add(humanBeing);
    }

    public void remove(HumanBeing humanBeing) {
        this.collection.remove(humanBeing);
    }

    public void removeFirst() {
        this.collection.remove();
    }

    public HumanBeing removeHead() {
        return this.collection.poll();
    }

    public void clear() {
        this.collection.clear();
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

    public Map<Integer, List<Exception>> readCollection(JsonArray jsonElements) throws Exception {
        Map<String, Handler<?>> handlers = new HandleManager().getHandlers();
        Map<String, Validator<?>> validators = new ValidatorManager().getValidators();

        int counter = -1;
        Map<Integer, List<Exception>> exceptionMap = new HashMap<>();
        Integer id = null;

        for (JsonElement jsonElement: jsonElements) {
            Map<String, Object> humanMap = new HashMap<>();
            counter += 1;
            List<Exception> exceptionList = new ArrayList<>();

            for (Field classField: HumanBeing.class.getDeclaredFields()) {
                if (id == null) id = counter;
                if (java.lang.reflect.Modifier.isStatic(classField.getModifiers())) continue;
                if (classField.getType() == Coordinates.class) continue;
                if (classField.getType() == Car.class) continue;
                try {
                    String jsonField = jsonElement.getAsJsonObject().get(classField.getName()).getAsString();
                    Object castedField = handlers.get(classField.getType().getSimpleName()).handle(jsonField, classField.getName());
                    Validator<Object> validator = (Validator<Object>) validators.get(classField.getName());
                    if (!validator.validate(castedField)) {
                        exceptionList.add(new WrongFieldsException(id, classField.getName()));
                        if (classField.getName().equals("id")) id = null;
                    }
                    humanMap.put(classField.getName(), castedField);
                } catch (UnsupportedOperationException e) {
                    exceptionList.add(new NullFieldException(id, classField.getName()));
                } catch (Exception e) {
                    exceptionList.add(e);
                }
            }

            for (Field classField: Coordinates.class.getDeclaredFields()) {
                try {
                    String jsonField = jsonElement.getAsJsonObject().get("coordinates").getAsJsonObject().get(classField.getName()).getAsString();
                    Object castedField = handlers.get(classField.getType().getSimpleName()).handle(jsonField, classField.getName());
                    Validator<Object> validator = (Validator<Object>) validators.get(classField.getName());
                    if (!validator.validate(castedField)) {
                        exceptionList.add(new WrongFieldsException(id, classField.getName()));
                        if (classField.getName().equals("id")) id = null;
                    }
                    humanMap.put("coordinates" + classField.getName(), castedField);
                } catch (UnsupportedOperationException e) {
                    exceptionList.add(new NullFieldException(id, classField.getName()));
                } catch (Exception e) {
                    exceptionList.add(e);
                }
            }

            for (Field classField: Car.class.getDeclaredFields()) {
                try {
                    String jsonField = jsonElement.getAsJsonObject().get("car").getAsJsonObject().get(classField.getName()).getAsString();
                    Object castedField = handlers.get(classField.getType().getSimpleName()).handle(jsonField, classField.getName());
                    Validator<Object> validator = (Validator<Object>) validators.get(classField.getName());
                    if (!validator.validate(castedField)) {
                        exceptionList.add(new WrongFieldsException(id, classField.getName()));
                        if (classField.getName().equals("id")) id = null;
                    }
                    humanMap.put("car" + classField.getName(), castedField);
                } catch (UnsupportedOperationException e) {
                    exceptionList.add(new NullFieldException(id, classField.getName()));
                } catch (Exception e) {
                    exceptionList.add(e);
                }
            }

            String name = (String) humanMap.get("name");
            Integer x = (Integer) humanMap.get("coordinatesx");
            Float y = (Float) humanMap.get("coordinatesy");
            LocalDate creationDate = (LocalDate) humanMap.get("creationDate");
            Boolean realHero = (Boolean) humanMap.get("realHero");
            Boolean hasToothpick = (Boolean) humanMap.get("hasToothpick");
            Integer impactSpeed = (Integer) humanMap.get("impactSpeed");
            Float minutesOfWaiting = (Float) humanMap.get("minutesOfWaiting");
            WeaponType weaponType = (WeaponType) humanMap.get("weaponType");
            Mood mood = (Mood) humanMap.get("mood");
            String nameCar = (String) humanMap.get("carname");
            Boolean coolCar = (Boolean) humanMap.get("carcool");

            if (exceptionList.isEmpty()) {
                Coordinates coordinates = new Coordinates(x, y);
                Car car = new Car(nameCar, coolCar);
                HumanBeing humanBeing = new HumanBeing(id, name, coordinates, creationDate, realHero, hasToothpick, impactSpeed, minutesOfWaiting, weaponType, mood, car);
                humanBeing.validate();
                this.add(humanBeing);
                this.initDate = LocalDateTime.now();
            } else {
                exceptionMap.put(id, exceptionList);
            }
        }
        return exceptionMap;
    }


    public void writeCollection(String ENV_KEY) throws AccessException, NotFoundException {
        jsonManager.writeFile(this.getCollection(), ENV_KEY);
    }
}
