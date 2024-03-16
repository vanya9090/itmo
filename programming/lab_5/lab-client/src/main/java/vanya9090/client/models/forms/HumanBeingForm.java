package vanya9090.client.models.forms;

import vanya9090.client.models.*;
import vanya9090.client.utils.ILogger;
import vanya9090.common.exceptions.EmptyFieldException;
import vanya9090.common.exceptions.ParseException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

/**
 * форма для ввода человека
 *
 * @author vanya9090
 */
public class HumanBeingForm implements Form {
    private final ILogger logger;
    private final Scanner scanner;
    boolean isExecute;

    public HumanBeingForm(ILogger logger, Scanner scanner, boolean isExecute) {
        this.logger = logger;
        this.scanner = scanner;
        this.isExecute = isExecute;
    }

    public HumanBeing create() throws ParseException, EmptyFieldException {

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

    private Car askCar() throws ParseException, EmptyFieldException {
        CarForm carForm = new CarForm(this.logger, this.scanner, this.isExecute);
        return carForm.create();
    }

    private Mood askMood() throws ParseException, EmptyFieldException {
        Mood mood;
        this.logger.info("Типы настрений: " + Arrays.toString(Mood.values()));
        while (true) {
            try {
                this.logger.field("Введите настроение: ");
                String field = this.scanner.nextLine().trim();
                if (field.isEmpty()) throw new EmptyFieldException("настроение");
                try {
                    mood = Mood.valueOf(field.toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new ParseException("mood", field);
                }
                break;
            } catch (EmptyFieldException | ParseException e) {
                if (this.isExecute) {
                    throw e;
                } else {
                    logger.error(e);
                }
            }
        }
        return mood;
    }

    private WeaponType askWeaponType() throws ParseException, EmptyFieldException {
        WeaponType weaponType;
        this.logger.info("Типы оружий: " + Arrays.toString(WeaponType.values()));
        while (true) {
            try {
                this.logger.field("Введите оружие: ");
//                Scanner scanner = new Scanner(System.in);
                String field = this.scanner.nextLine().trim();
                if (field.isEmpty()) throw new EmptyFieldException("оружие");
                try {
                    weaponType = WeaponType.valueOf(field.toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new ParseException("weaponType", field);
                }
                break;
            } catch (EmptyFieldException | ParseException e) {
                if (this.isExecute) {
                    throw e;
                } else {
                    logger.error(e);
                }
            }
        }
        return weaponType;
    }

    private Float askMinutesOfWaiting() throws ParseException {
        Float minutes;
        while (true) {
            try {
                this.logger.field("Введите время ожидания персонажа: ");
//                Scanner scanner = new Scanner(System.in);
                String field = this.scanner.nextLine().trim();
                if (field.isEmpty()) {
                    minutes = null;
                } else {
                    try {
                        minutes = Float.parseFloat(field);
                    } catch (IllegalArgumentException e) {
                        throw new ParseException("minutes", field);
                    }
                }
                break;
            } catch (ParseException e) {
                if (this.isExecute) {
                    throw e;
                } else {
                    logger.error(e);
                }
            }
        }
        return minutes;
    }

    private int askImpactSpeed() throws ParseException, EmptyFieldException {
        int speed;
        while (true) {
            try {
                this.logger.field("Введите скорость удара: ");
//                Scanner scanner = new Scanner(System.in);
                String field = this.scanner.nextLine().trim();
                if (field.isEmpty()) throw new EmptyFieldException("скорость удара");
                try {
                    speed = Integer.parseInt(field);
                } catch (IllegalArgumentException e) {
                    throw new ParseException("speed", field);
                }
                break;
            } catch (EmptyFieldException | ParseException e) {
                if (this.isExecute) {
                    throw e;
                } else {
                    logger.error(e);
                }
            }
        }
        return speed;
    }

    private boolean askHasToothpick() throws ParseException, EmptyFieldException {
        boolean toothpick;
        while (true) {
            try {
                this.logger.field("Есть ли зубочистка?(true/false): ");
//                Scanner scanner = new Scanner(System.in);
                String field = this.scanner.nextLine().trim();
                if (field.isEmpty()) throw new EmptyFieldException("зубочистка");
                if (!field.equals("true") && !field.equals("false")) throw new ParseException("hasToothPick", field);
                try {
                    toothpick = Boolean.parseBoolean(field);
                } catch (IllegalArgumentException e) {
                    throw new ParseException("toothpick", field);
                }
                break;
            } catch (EmptyFieldException | ParseException e) {
                if (this.isExecute) {
                    throw e;
                } else {
                    logger.error(e);
                }
            }
        }
        return toothpick;
    }

    private boolean askRealHero() throws ParseException, EmptyFieldException {
        boolean hero;
        while (true) {
            try {
                this.logger.field("Настоящий герой?(true/false): ");
//                Scanner scanner = new Scanner(System.in);
                String field = this.scanner.nextLine().trim();
                if (field.isEmpty()) throw new EmptyFieldException("зубочистка");
                if (!field.equals("true") && !field.equals("false")) throw new ParseException("realHero", field);
                try {
                    hero = Boolean.parseBoolean(field);
                } catch (IllegalArgumentException e) {
                    throw new ParseException("hero", field);
                }
                break;
            } catch (EmptyFieldException | ParseException e) {
                if (this.isExecute) {
                    throw e;
                } else {
                    logger.error(e);
                }
            }
        }
        return hero;
    }

    private Coordinates askCoordinates() throws ParseException, EmptyFieldException {
        CoordinatesForm coordinatesForm = new CoordinatesForm(this.logger, this.scanner, this.isExecute);
        return coordinatesForm.create();
    }

    public String askName() throws EmptyFieldException {
        String name;
        while (true) {
            try {
                this.logger.field("Введите имя: ");
//                Scanner scanner = new Scanner(System.in);
                name = this.scanner.nextLine().trim();
                if (name.isEmpty()) throw new EmptyFieldException("имя");
                break;
            } catch (EmptyFieldException e) {
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
