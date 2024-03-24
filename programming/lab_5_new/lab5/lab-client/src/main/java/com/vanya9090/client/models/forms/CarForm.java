package com.vanya9090.client.models.forms;

import com.vanya9090.client.exceptions.EmptyFieldException;
import com.vanya9090.client.exceptions.ParseException;
import com.vanya9090.client.exceptions.WrongFieldsException;
import com.vanya9090.client.handlers.BooleanHandler;
import com.vanya9090.client.handlers.StringHandler;
import com.vanya9090.client.models.Car;
import com.vanya9090.client.utils.ILogger;
import com.vanya9090.client.utils.Logger;
import com.vanya9090.client.validators.CoolCarValidator;
import com.vanya9090.client.validators.NameCarValidator;

import java.util.Scanner;

public class CarForm implements Form {
    private final ILogger logger;
    private final Scanner scanner;
    private final boolean isExecute;
    public CarForm(ILogger logger, Scanner scanner, boolean isExecute) {
        this.logger = logger;
        this.scanner = scanner;
        this.isExecute = isExecute;
    }

    @Override
    public Car create() throws WrongFieldsException, EmptyFieldException, ParseException {
        return new Car(this.askName(), this.askCool());
    }

    public String askName() throws WrongFieldsException, EmptyFieldException {
        String name;
        StringHandler stringHandler = new StringHandler();
        NameCarValidator nameCarValidator = new NameCarValidator();
        while (true) {
            try {
                this.logger.field("Введите имя машины: ");
//                Scanner scanner = new Scanner(System.in);
                String field = this.scanner.nextLine().trim();
                name = stringHandler.handle(field, "carName");
                if (!nameCarValidator.validate(name)) throw new WrongFieldsException(0, "nameCar");
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

    public Boolean askCool() throws WrongFieldsException, ParseException, EmptyFieldException {
        boolean cool;
        BooleanHandler booleanHandler = new BooleanHandler();
        CoolCarValidator coolCarValidator = new CoolCarValidator();
        while (true) {
            try {
                this.logger.field("Машина хорошая?(true/false): ");
//                Scanner scanner = new Scanner(System.in);
                String field = this.scanner.nextLine().trim();
                cool = booleanHandler.handle(field, "coolCar");
                if (!coolCarValidator.validate(cool)) throw new WrongFieldsException(0, "coolCar");
                cool = Boolean.parseBoolean(field);
                break;
            } catch (ParseException | EmptyFieldException | WrongFieldsException e) {
                if (this.isExecute) {
                    throw e;
                } else {
                    logger.error(e);
                }
            }
        }
        return cool;
    }
}