package vanya9090.client.forms;

import vanya9090.common.exceptions.EmptyFieldException;
import vanya9090.common.exceptions.WrongFieldsException;
import vanya9090.common.handlers.HandleManager;
import vanya9090.common.handlers.Handler;
import vanya9090.common.util.ILogger;
import vanya9090.common.validators.Validator;
import vanya9090.common.validators.ValidatorManager;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class ParentForm {
    private final ILogger logger;
    private final Scanner scanner;
    boolean isExecute;
    Map<String, Handler<?>> handlers;
    Map<String, Validator<?>> validators;

    public ParentForm(ILogger logger, Scanner scanner, boolean isExecute) {
        this.logger = logger;
        this.scanner = scanner;
        this.isExecute = isExecute;
        this.handlers = new HandleManager().getHandlers();
        this.validators = new ValidatorManager().getValidators();
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
    public abstract Object create() throws Exception;
    public abstract Map<String, Object> askAll() throws Exception;
}
