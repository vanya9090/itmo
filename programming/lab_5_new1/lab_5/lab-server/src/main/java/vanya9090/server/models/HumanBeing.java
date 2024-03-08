package vanya9090.server.models;

import vanya9090.server.managers.CollectionManager;

import java.time.LocalDate;
import java.util.Objects;

public class HumanBeing implements Comparable<HumanBeing> {
    private static int nextId;
    private final Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
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

    public static void updateNextId(CollectionManager collectionManager) {
        int maxId = collectionManager
                .getCollection()
                .stream().filter(Objects::nonNull)
                .map(HumanBeing::getId)
                .mapToInt(Integer::intValue).max().orElse(0);
        nextId = maxId + 1;
    }

    public int getId() {
        return this.id;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public int getImpactSpeed() {
        return this.impactSpeed;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

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
}
