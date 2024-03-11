package com.vanya9090.client.managers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.vanya9090.client.exceptions.*;
import com.vanya9090.client.handlers.*;
import com.vanya9090.client.models.*;
import com.vanya9090.client.validators.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Deque;

public class CollectionManager {
    private final JSONManager jsonManager;
    private Deque<HumanBeing> collection = new ArrayDeque<HumanBeing>();
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
            if (element.getId() == id) {
                return element;
            }
        }
        return null;
    }

    public void readCollection(JsonArray jsonElements) throws WrongFieldsException, ReadException, WrongPathException, EmptyFieldException, ParseException, NullFieldException {
        Handler<Integer> intHandler = new IntHandler();
        Handler<Boolean> booleanHandler = new BooleanHandler();
        Handler<String> stringHandler = new StringHandler();
        Handler<Float> floatHandler = new FloatHandler();
        Handler<LocalDate> localDateHandler = new LocalDateHandler();
        Handler<WeaponType> weaponTypeHandler = new WeaponTypeHandler();
        Handler<Mood> moodHandler = new MoodHandler();

        Validator<Integer> idValidator = new IdValidator();
        Validator<String> nameValidator = new NameValidator();
        Validator<Integer> xValidator = new XValidator();
        Validator<Float> yValidator = new YValidator();
        Validator<LocalDate> creationDateValidator = new CreationDateValidator();
        Validator<Boolean> realHeroValidator = new RealHeroValidator();
        Validator<Boolean> hasToothpickValidator = new HasToothpickValidator();
        Validator<Integer> impactSpeedValidator = new ImpactSpeedValidator();
        Validator<Float> minutesOfWaitingValidator = new MinutesOfWaitingValidator();
        Validator<WeaponType> weaponTypeValidator = new WeaponTypeValidator();
        Validator<Mood> moodValidator = new MoodValidator();
        Validator<String> nameCarValidator = new NameCarValidator();
        Validator<Boolean> coolCarValidator = new CoolCarValidator();

        Integer id;
        String name;
        Integer x;
        Float y;
        LocalDate creationDate;
        Boolean realHero;
        Boolean hasToothpick;
        Integer impactSpeed;
        Float minutesOfWaiting;
        WeaponType weaponType;
        Mood mood;
        String nameCar;
        Boolean coolCar;

        for (JsonElement jsonElement: jsonElements) {
            try {
                String fieldId = jsonElement.getAsJsonObject().get("id").getAsString();
                id = intHandler.handle(fieldId, "id");
                if (!idValidator.validate(id)) throw new WrongFieldsException(0, "id");
            } catch (UnsupportedOperationException e) {
                throw new NullFieldException("id");
            }

            try {
                String fieldName = jsonElement.getAsJsonObject().get("name").getAsString();
                name = stringHandler.handle(fieldName, "name");
                if (!nameValidator.validate(name)) throw new WrongFieldsException(id, "name");
            } catch (UnsupportedOperationException e) {
                throw new NullFieldException("name");
            }

            try {
                String fieldX = jsonElement.getAsJsonObject().get("coordinates").getAsJsonObject().get("x").getAsString();
                x = intHandler.handle(fieldX, "x");
                if (!xValidator.validate(x)) throw new WrongFieldsException(id, "x");
            } catch (UnsupportedOperationException e) {
                throw new NullFieldException("x");
            }

            try {
                String fieldY = jsonElement.getAsJsonObject().get("coordinates").getAsJsonObject().get("y").getAsString();
                y = floatHandler.handle(fieldY, "y");
                if (!yValidator.validate(y)) throw new WrongFieldsException(id, "y");
            } catch (UnsupportedOperationException e) {
                throw new NullFieldException("y");
            }

            try {
                String fieldCreationDate = jsonElement.getAsJsonObject().get("creationDate").getAsString();
                creationDate = localDateHandler.handle(fieldCreationDate, "creationDate");
                if (!creationDateValidator.validate(creationDate)) throw new WrongFieldsException(id, "creationDate");
            } catch (UnsupportedOperationException e) {
                throw new NullFieldException("creationDate");
            }

            try {
                String fieldRealHero = jsonElement.getAsJsonObject().get("realHero").getAsString();
                realHero = booleanHandler.handle(fieldRealHero, "realHero");
                if (!realHeroValidator.validate(realHero)) throw new WrongFieldsException(id, "realHero");
            } catch (UnsupportedOperationException e) {
//                realHero = null;
                throw new NullFieldException("realHero");
            }

            try {
                String fieldHasToothpick = jsonElement.getAsJsonObject().get("hasToothpick").getAsString();
                hasToothpick = booleanHandler.handle(fieldHasToothpick, "hasToothpick");
                if (!hasToothpickValidator.validate(hasToothpick)) throw new WrongFieldsException(id, "hasToothpick");
            } catch (UnsupportedOperationException e) {
//                hasToothpick = null;
                throw new NullFieldException("hasToothpick");
            }


            try {
                String fieldImpactSpeed = jsonElement.getAsJsonObject().get("impactSpeed").getAsString();
                impactSpeed = intHandler.handle(fieldImpactSpeed, "impactSpeed");
                if (!impactSpeedValidator.validate(impactSpeed)) throw new WrongFieldsException(id, "impactSpeed");
            } catch (UnsupportedOperationException e) {
//                impactSpeed = null;
                throw new NullFieldException("impactSpeed");
            }

            try {
                String fieldMinutesOfWaiting = jsonElement.getAsJsonObject().get("minutesOfWaiting").getAsString();
                minutesOfWaiting = floatHandler.handle(fieldMinutesOfWaiting, "minutesOfWaiting");
                if (!minutesOfWaitingValidator.validate(minutesOfWaiting)) throw new WrongFieldsException(id, "minutesOfWaiting");
            } catch (UnsupportedOperationException e) {
//                minutesOfWaiting = null;
                throw new NullFieldException("minutesOfWaiting");
            }

            try {
                String fieldWeaponType = jsonElement.getAsJsonObject().get("weaponType").getAsString();
                weaponType = weaponTypeHandler.handle(fieldWeaponType, "weaponType");
                if (!weaponTypeValidator.validate(weaponType)) throw new WrongFieldsException(id, "weaponType");
            } catch (UnsupportedOperationException e) {
                throw new NullFieldException("weaponType");
            }


            try {
                String fieldMood = jsonElement.getAsJsonObject().get("mood").getAsString();
                mood = moodHandler.handle(fieldMood, "mood");
                if (!moodValidator.validate(mood)) throw new WrongFieldsException(id, "mood");
            } catch (UnsupportedOperationException e) {
                throw new NullFieldException("mood");
            }


            try {
                String fieldNameCar = jsonElement.getAsJsonObject().get("car").getAsJsonObject().get("name").getAsString();
                nameCar = stringHandler.handle(fieldNameCar, "nameCar");
                if (!nameCarValidator.validate(nameCar)) throw new WrongFieldsException(id, "nameCar");
            } catch (UnsupportedOperationException e) {
                throw new NullFieldException("nameCar");
            }


            try {
                String fieldCoolCar = jsonElement.getAsJsonObject().get("car").getAsJsonObject().get("cool").getAsString();
                coolCar = booleanHandler.handle(fieldCoolCar, "coolCar");
                if (!coolCarValidator.validate(coolCar)) throw new WrongFieldsException(id, "coolCar");
            } catch (UnsupportedOperationException e) {
                throw new NullFieldException("coolCar");
            }

            Coordinates coordinates = new Coordinates(x, y);
            Car car = new Car(nameCar, coolCar);
            this.add(new HumanBeing(id, name, coordinates, creationDate, realHero, hasToothpick, impactSpeed, minutesOfWaiting, weaponType, mood, car));
        }
        this.initDate = LocalDateTime.now();
    }
}
