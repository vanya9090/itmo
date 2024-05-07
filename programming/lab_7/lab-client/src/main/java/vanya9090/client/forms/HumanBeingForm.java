package vanya9090.client.forms;

import vanya9090.common.exceptions.NullFieldException;
import vanya9090.common.exceptions.WrongFieldsException;
import vanya9090.common.handlers.FloatHandler;
import vanya9090.common.handlers.HandleManager;
import vanya9090.common.handlers.Handler;
import vanya9090.common.handlers.StringHandler;
import vanya9090.common.models.*;
import vanya9090.common.util.ILogger;
import vanya9090.common.exceptions.EmptyFieldException;
import vanya9090.common.exceptions.ParseException;
import vanya9090.common.validators.MinutesOfWaitingValidator;
import vanya9090.common.validators.Validator;
import vanya9090.common.validators.ValidatorManager;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.*;

/**
 * форма для ввода человека
 * @author vanya9090
 */
public class HumanBeingForm implements Form {
    private final ILogger logger;
    private final Scanner scanner;
    boolean isExecute;
    Map<String, Handler<?>> handlers;
    Map<String, Validator<?>> validators;

    public HumanBeingForm(ILogger logger, Scanner scanner, boolean isExecute) {
        this.logger = logger;
        this.scanner = scanner;
        this.isExecute = isExecute;
        handlers = new HandleManager().getHandlers();
        validators = new ValidatorManager().getValidators();
    }

    public HumanBeing create() throws Exception {
        return new HumanBeing(this.askAll());
    }

    public Map<String, Object> fieldCircle(Field classField) throws WrongFieldsException {
        while (true) {

            logger.info("Введите " + classField.getName() + ": ");

            String field = this.scanner.nextLine().trim();
            try {
                if (field.isEmpty()) throw new EmptyFieldException(classField.getName());

                Object castedField = handlers.get(classField.getType().getSimpleName()).handle(field, classField.getName());
                Validator<Object> validator = (Validator<Object>) validators.get(classField.getName());
                if (!validator.validate(castedField)) throw new WrongFieldsException(0, classField.getName());
                Map<String, Object> fieldMap = new HashMap<>();
                fieldMap.put(classField.getName(), castedField);

                return fieldMap;
            } catch (Exception e) {
                if (isExecute) break;
                logger.error(e);
            }
        }
        throw new WrongFieldsException(0, classField.getName());
    }

    public Map<String, Object> askAll() throws Exception {
        Map<String, Object> humanMap = new HashMap<>();
        Map<String, Object> fieldMap;

        for (Field classField : HumanBeing.class.getDeclaredFields()) {
            if (classField.getName().equals("nextId") || classField.getName().equals("id") || classField.getName().equals("creationDate")) continue;
            if (classField.getName().equals("coordinates")) {
                humanMap.put(classField.getName(), askCoordinates());
                continue;
            } else if (classField.getName().equals("car")) {
                humanMap.put(classField.getName(), askCar());
                continue;
            }
            if (classField.getType() == WeaponType.class) {
                logger.info(Arrays.toString(WeaponType.values()));
            } else if (classField.getType() == Mood.class) {
                logger.info(Arrays.toString(Mood.values()));
            }
            fieldMap = this.fieldCircle(classField);
            humanMap.put(classField.getName(), fieldMap.get(classField.getName()));
        }
        return humanMap;
    }

    private Car askCar() throws ParseException, EmptyFieldException, WrongFieldsException {
        Map<String, Object> carMap = new HashMap<>();
        Map<String, Object> fieldMap;
        logger.info("Машина:");
        for (Field classField : Car.class.getDeclaredFields()) {
            if (classField.getName().equals("id")) continue;
            if (classField.getName().equals("nextId")) continue;

            fieldMap = this.fieldCircle(classField);
            carMap.put(classField.getName(), fieldMap.get(classField.getName()));
        }
        return new Car(carMap);
    }

    private Coordinates askCoordinates() throws ParseException, EmptyFieldException, WrongFieldsException {
        Map<String, Object> coordinatesMap = new HashMap<>();
        Map<String, Object> fieldMap;
        logger.info("Координаты:");
        for (Field classField : Coordinates.class.getDeclaredFields()) {
            if (classField.getName().equals("id")) continue;
            if (classField.getName().equals("nextId")) continue;

            fieldMap = this.fieldCircle(classField);
            coordinatesMap.put(classField.getName(), fieldMap.get(classField.getName()));
        }
        return new Coordinates(coordinatesMap);
    }
}
