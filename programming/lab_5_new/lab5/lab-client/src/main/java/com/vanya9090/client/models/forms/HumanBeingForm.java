package com.vanya9090.client.models.forms;

import com.vanya9090.client.exceptions.EmptyFieldException;
import com.vanya9090.client.exceptions.ParseException;
import com.vanya9090.client.exceptions.WrongFieldsException;
import com.vanya9090.client.handlers.*;
import com.vanya9090.client.models.HumanBeing;
import com.vanya9090.client.models.Car;
import com.vanya9090.client.models.Coordinates;
import com.vanya9090.client.models.Mood;
import com.vanya9090.client.models.WeaponType;
import com.vanya9090.client.utils.ILogger;
import com.vanya9090.client.utils.Logger;
import com.vanya9090.client.validators.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class HumanBeingForm implements Form {
    private final ILogger logger;
    private final Scanner scanner;
    boolean isExecute;
    public HumanBeingForm(ILogger logger, Scanner scanner, boolean isExecute) {
        this.logger = logger;
        this.scanner = scanner;
        this.isExecute = isExecute;
    }

    public HumanBeing create() throws WrongFieldsException, EmptyFieldException, ParseException {

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

    private Car askCar() throws WrongFieldsException, EmptyFieldException, ParseException {
        CarForm carForm = new CarForm(this.logger, this.scanner, this.isExecute);
        return carForm.create();
    }

    private Mood askMood() throws WrongFieldsException, ParseException, EmptyFieldException {
        Mood mood;
        MoodHandler moodHandler = new MoodHandler();
        MoodValidator moodValidator = new MoodValidator();
        this.logger.info("Типы настроений: " + Arrays.toString(Mood.values()));
        while (true) {
            try {
                this.logger.field("Введите настроение: ");
//                Scanner scanner = new Scanner(System.in);
                String field = this.scanner.nextLine().trim();
                mood = moodHandler.handle(field, "mood");
                if (!moodValidator.validate(mood)) throw new WrongFieldsException(0, "mood");
                break;
            } catch (ParseException | EmptyFieldException | WrongFieldsException e) {
                if (this.isExecute) {
                    throw e;
                } else {
                    logger.error(e);
                }
            }
        }
        return mood;
    }

    private WeaponType askWeaponType() throws WrongFieldsException, ParseException, EmptyFieldException {
        WeaponType weaponType;
        WeaponTypeHandler weaponTypeHandler = new WeaponTypeHandler();
        WeaponTypeValidator weaponTypeValidator = new WeaponTypeValidator();
        this.logger.info("Типы оружий: " + Arrays.toString(WeaponType.values()));
        while (true) {
            try {
                this.logger.field("Введите оружие: ");
//                Scanner scanner = new Scanner(System.in);
                String field = this.scanner.nextLine().trim();
                weaponType = weaponTypeHandler.handle(field, "weaponType");
                if (!weaponTypeValidator.validate(weaponType)) throw new WrongFieldsException(0, "weaponType");
                break;
            } catch (ParseException | EmptyFieldException | WrongFieldsException e) {
                if (this.isExecute) {
                    throw e;
                } else {
                    logger.error(e);
                }
            }
        }
        return weaponType;
    }

    private Float askMinutesOfWaiting() throws WrongFieldsException, ParseException, EmptyFieldException {
        Float minutesOfWaiting;
        FloatHandler floatHandler = new FloatHandler();
        MinutesOfWaitingValidator minutesOfWaitingValidator = new MinutesOfWaitingValidator();
        while (true) {
            try {
                this.logger.field("Введите время ожидания персонажа: ");
//                Scanner scanner = new Scanner(System.in);
                String field = this.scanner.nextLine().trim();
                minutesOfWaiting = floatHandler.handle(field, "minutesOfWaiting");
                if (!minutesOfWaitingValidator.validate(minutesOfWaiting)) throw new WrongFieldsException(0, "minutesOfWaiting");
                break;
            } catch (ParseException | EmptyFieldException | WrongFieldsException e) {
                if (this.isExecute) {
                    throw e;
                } else {
                    logger.error(e);
                }
            }
        }
        return minutesOfWaiting;
    }

    private int askImpactSpeed() throws WrongFieldsException, ParseException, EmptyFieldException {
        int impactSpeed;
        IntHandler intHandler = new IntHandler();
        ImpactSpeedValidator impactSpeedValidator = new ImpactSpeedValidator();
        while (true) {
            try {
                this.logger.field("Введите скорость удара: ");
//                Scanner scanner = new Scanner(System.in);
                String field = this.scanner.nextLine().trim();
                impactSpeed = intHandler.handle(field, "impactSpeed");
                if (!impactSpeedValidator.validate(impactSpeed)) throw new WrongFieldsException(0, "impactSpeed");
                break;
            } catch (ParseException | EmptyFieldException | WrongFieldsException e) {
                if (this.isExecute) {
                    throw e;
                } else {
                    logger.error(e);
                }
            }
        }
        return impactSpeed;
    }

    private boolean askHasToothpick() throws WrongFieldsException, ParseException, EmptyFieldException {
        boolean hasToothpick;
        BooleanHandler booleanHandler = new BooleanHandler();
        HasToothpickValidator hasToothpickValidator = new HasToothpickValidator();
        while (true) {
            try {
                this.logger.field("Есть ли зубочистка?(true/false): ");
//                Scanner scanner = new Scanner(System.in);
                String field = this.scanner.nextLine().trim();
                hasToothpick = booleanHandler.handle(field, "hasToothpick");
                if (!hasToothpickValidator.validate(hasToothpick)) throw new WrongFieldsException(0, "hasToothpick");
                break;
            } catch (ParseException | EmptyFieldException | WrongFieldsException e) {
                if (this.isExecute) {
                    throw e;
                } else {
                    logger.error(e);
                }
            }
        }
        return hasToothpick;
    }

    private boolean askRealHero() throws WrongFieldsException, ParseException, EmptyFieldException {
        boolean realHero;
        BooleanHandler booleanHandler = new BooleanHandler();
        RealHeroValidator realHeroValidator = new RealHeroValidator();
        while (true) {
            try {
                this.logger.field("Настоящий герой?(true/false): ");
//                Scanner scanner = new Scanner(System.in);
                String field = this.scanner.nextLine().trim();
                realHero = booleanHandler.handle(field, "realHero");
                if (!realHeroValidator.validate(realHero)) throw new WrongFieldsException(0, "realHero");
                break;
            } catch (ParseException | EmptyFieldException | WrongFieldsException e) {
                if (this.isExecute) {
                    throw e;
                } else {
                    logger.error(e);
                }
            }
        }
        return realHero;
    }

    private Coordinates askCoordinates() throws WrongFieldsException, ParseException, EmptyFieldException {
        CoordinatesForm coordinatesForm = new CoordinatesForm(this.logger, this.scanner, this.isExecute);
        return coordinatesForm.create();
    }

    public String askName() throws WrongFieldsException, EmptyFieldException {
        String name;
        StringHandler stringHandler = new StringHandler();
        NameValidator nameValidator = new NameValidator();
        while (true) {
            try {
                this.logger.field("Введите имя: ");
//                Scanner scanner = new Scanner(System.in);
                String field = this.scanner.nextLine().trim();
                name = stringHandler.handle(field, "name");
                if (!nameValidator.validate(name)) throw new WrongFieldsException(0, "name");
                break;
            } catch (EmptyFieldException | WrongFieldsException e) {
                if (this.isExecute) {
                    throw e;
                } else {
                    logger.error(e);
                }
            }
        }
        return name;
    }
}