package models.forms;

import exceptions.BooleanFormatException;
import exceptions.EmptyFieldException;
import models.*;
import utils.Logger;

import java.nio.charset.CoderResult;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Scanner;

public class HumanBeingForm implements Form{
    private final Logger logger;

    public HumanBeingForm(Logger logger) {
        this.logger = logger;
    }

    public HumanBeing create(){

        return new HumanBeing(
                this.askName(),
                this.askCoordinates(),
                LocalDate.now(),
                this.askRealHero(),
                this.askHasToothpick(),
                this.askImpactSpeed(),
                this.askMinutesOfWaiting(),
                this.askWeaponType(),
                this.askMood(),
                this.askCar());
    }

    private Car askCar() {
        CarForm carForm = new CarForm(this.logger);
        return carForm.create();
    }

    private Mood askMood() {
        Mood mood;
        this.logger.info("Типы настрений: " + Arrays.toString(Mood.values()));
        while (true){
            try {
                this.logger.field("Введите настроение: ");
                Scanner scanner = new Scanner(System.in);
                String field = scanner.nextLine().trim();
                if (field.isEmpty()) throw new EmptyFieldException("настроение");
                mood = Mood.valueOf(field.toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                logger.error("Такого типа настроения нет");
            } catch (EmptyFieldException e){
                logger.error(e);
            }
        }
        return mood;
    }

    private WeaponType askWeaponType() {
        WeaponType weaponType;
        this.logger.info("Типы оружий: " + Arrays.toString(WeaponType.values()));
        while (true){
            try {
                this.logger.field("Введите оружие: ");
                Scanner scanner = new Scanner(System.in);
                String field = scanner.nextLine().trim();
                if (field.isEmpty()) throw new EmptyFieldException("оружие");
                weaponType = WeaponType.valueOf(field.toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                logger.error("Такого типа оружия нет");
            } catch (EmptyFieldException e){
                logger.error(e);
            }
        }
        return weaponType;
    }

    private Float askMinutesOfWaiting() {
        Float minutes;
        while (true){
            try {
                this.logger.field("Введите время ожидания персонажа: ");
                Scanner scanner = new Scanner(System.in);
                String field = scanner.nextLine().trim();
                if (field.isEmpty()){
                    minutes = null;
                }else {
                    minutes = Float.parseFloat(field);
                }
                break;
            } catch (NumberFormatException e) {
                logger.error("Минуты должны быть представлены числом");
            }
        }
        return minutes;
    }

    private int askImpactSpeed() {
        int speed;
        while (true){
            try {
                this.logger.field("Введите скорость удара: ");
                Scanner scanner = new Scanner(System.in);
                String field = scanner.nextLine().trim();
                if (field.isEmpty()) throw new EmptyFieldException("скорость удара");
                speed = Integer.parseInt(field);
                break;
            } catch (NumberFormatException e) {
                logger.error("Скорость удара должна быть представлена целым числом");
            } catch (EmptyFieldException e){
                logger.error(e);
            }
        }
        return speed;
    }

    private boolean askHasToothpick() {
        boolean toothpick;
        while (true){
            try {
                this.logger.field("Есть ли зубочистка?(true/false): ");
                Scanner scanner = new Scanner(System.in);
                String field = scanner.nextLine().trim();
                if (field.isEmpty()) throw new EmptyFieldException("зубочистка");
                if (!field.equals("true") && !field.equals("false")) throw new BooleanFormatException();
                toothpick = Boolean.parseBoolean(field);
                break;
            } catch (EmptyFieldException | BooleanFormatException e){
                logger.error(e);
            }
        }
        return toothpick;
    }

    private boolean askRealHero() {
        boolean hero;
        while (true){
            try {
                this.logger.field("Настоящий герой?(true/false): ");
                Scanner scanner = new Scanner(System.in);
                String field = scanner.nextLine().trim();
                if (field.isEmpty()) throw new EmptyFieldException("зубочистка");
                if (!field.equals("true") && !field.equals("false")) throw new BooleanFormatException();
                hero = Boolean.parseBoolean(field);
                break;
            } catch (EmptyFieldException | BooleanFormatException e){
                logger.error(e);
            }
        }
        return hero;
    }
    private Coordinates askCoordinates() {
        CoordinatesForm coordinatesForm = new CoordinatesForm(this.logger);
        return coordinatesForm.create();
    }

    public String askName(){
        String name;
        while (true){
            try {
                this.logger.field("Введите имя: ");
                Scanner scanner = new Scanner(System.in);
                name = scanner.nextLine().trim();
                if (name.isEmpty()) throw new EmptyFieldException("имя");
                break;
            } catch (EmptyFieldException e) {
                logger.error(e);
            }
        }
        return name;
    }

}
