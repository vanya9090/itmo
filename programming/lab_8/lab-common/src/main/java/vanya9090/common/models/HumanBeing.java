package vanya9090.common.models;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HumanBeing implements Comparable<HumanBeing>, Validatable, Serializable {
    static int nextId;
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private boolean realHero;
    private boolean hasToothpick;
    private int impactSpeed;
    private float minutesOfWaiting;
    private WeaponType weaponType; //Поле не может быть null
    private Mood mood; //Поле может быть null
    private Car car; //Поле может быть null

    public HumanBeing(String name, Coordinates coordinates, LocalDate creationDate,
                      boolean realHero, boolean hasToothpick, int impactSpeed,
                      Float minutesOfWaiting, WeaponType weaponType, Mood mood, Car car) {
        this.id = nextId;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.minutesOfWaiting = minutesOfWaiting;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
    }

    public HumanBeing(int id, String name, Coordinates coordinates, LocalDate creationDate,
                      boolean realHero, boolean hasToothpick, int impactSpeed,
                      Float minutesOfWaiting, WeaponType weaponType, Mood mood, Car car) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.minutesOfWaiting = minutesOfWaiting;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
    }

    public HumanBeing(Map<String, Object> humanMap) {
        this.id = nextId;
        this.name = (String) humanMap.get("name");
        this.coordinates = (Coordinates) humanMap.get("coordinates");
        this.creationDate = LocalDate.now();
        this.realHero = (Boolean) humanMap.get("realHero");
        this.hasToothpick = (Boolean) humanMap.get("hasToothpick");
        this.impactSpeed = (Integer) humanMap.get("impactSpeed");
        this.minutesOfWaiting = (Float) humanMap.get("minutesOfWaiting");
        this.weaponType = (WeaponType) humanMap.get("weaponType");
        this.mood = (Mood) humanMap.get("mood");
        this.car = (Car) humanMap.get("car");
    }

    public void setId(int id) {
        this.id = id;
    }

//    public static void updateNextId() {
//        int maxId = collectionManager
//                .getCollection()
//                .stream().filter(Objects::nonNull)
//                .map(HumanBeing::getId)
//                .mapToInt(Integer::intValue).max().orElse(0);
//        nextId = maxId + 1;
//    }

    public Map<String, Object> getHumanMap() {
        Map<String, Object> humanMap = new HashMap<>();
        humanMap.put("HumanBeing id", id.toString());
        humanMap.put("HumanBeing name", name);
        humanMap.put("Coordinates x", coordinates.getX().toString());
        humanMap.put("Coordinates y", coordinates.getY().toString());
        humanMap.put("HumanBeing realHero", Boolean.toString(realHero));
        humanMap.put("HumanBeing hasToothpick", Boolean.toString(hasToothpick));
        humanMap.put("HumanBeing impactSpeed", Integer.toString(impactSpeed));
        humanMap.put("HumanBeing minutesOfWaiting", Float.toString(minutesOfWaiting));
        humanMap.put("HumanBeing creationDate", creationDate.toString());
        humanMap.put("HumanBeing weaponType", weaponType.toString());
        humanMap.put("HumanBeing mood", mood.toString());
        humanMap.put("Car cool", car.getCool().toString());
        humanMap.put("Car name", car.getName());
        return humanMap;
    }
    public int getId() {
        return this.id;
    }
    public String getName() {return this.name;}
    public Boolean getRealHero() {return this.realHero;}
    public Boolean getHasToothpick() {return this.hasToothpick;}
    public float getMinutesOfWaiting() {return this.minutesOfWaiting;}
    public Coordinates getCoordinates() {
        return this.coordinates;
    }
    public int getImpactSpeed() {return this.impactSpeed;}
    public WeaponType getWeaponType() {return weaponType;}
    public Mood getMood() {return this.mood;}
    public Car getCar() {return this.car;}
    public LocalDate getCreationDate() {return this.creationDate;}
//    public User getOwner() {return }

    public void update(HumanBeing humanBeing) {
        this.name = humanBeing.name;
        this.coordinates = humanBeing.coordinates;
        this.creationDate = humanBeing.creationDate;
        this.realHero = humanBeing.realHero;
        this.hasToothpick = humanBeing.hasToothpick;
        this.impactSpeed = humanBeing.impactSpeed;
        this.minutesOfWaiting = humanBeing.minutesOfWaiting;
        this.weaponType = humanBeing.weaponType;
        this.mood = humanBeing.mood;
        this.car = humanBeing.car;
    }

    @Override
    public String toString() {
        String info = "";
        info += "Человек №" + id;
        info += " (добавлен " + creationDate.toString() + ")";
        info += "\n\tИмя: " + name;
        info += "\n\tМестоположение: " + coordinates;
        info += "\n\tНастоящий герой?: " + realHero;
        info += "\n\tЕсть зубочистка?: " + hasToothpick;
        info += "\n\tСкорость удара: " + impactSpeed;
        info += "\n\tЖдет: " + minutesOfWaiting + " минут";
        info += "\n\tТип оружия:  " + weaponType.name();
        info += "\n\tНастроение:  " + mood.name();
        info += "\n\tНзавание машины:  " + car.getName();
        info += "\n\tХорошая машина?:  " + car.getCool();
        return info;
    }

    @Override
    public int compareTo(HumanBeing humanBeing) {
        return (int) (this.getCoordinates().getDistance() - humanBeing.getCoordinates().getDistance());
    }

    @Override
    public boolean validate() {
        if (this.name == null || this.weaponType == null || this.mood == null || this.id == null) return false;
        if (this.id < 0) return false;
        if (this.name.isEmpty()) return false;
        if (!this.coordinates.validate()) return false;
        if (this.creationDate == null) return false;
        if (this.impactSpeed <= 0) return false;
        if (this.minutesOfWaiting <= 0) return false;
//        if (!this.weaponType.validate()) return false;
//        if (!this.mood.validate()) return false;
        if (!this.car.validate()) return false;
        return true;
    }
}
