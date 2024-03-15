package vanya9090.client.models.forms;

import vanya9090.common.exceptions.BooleanFormatException;
import vanya9090.common.exceptions.EmptyFieldException;
import vanya9090.common.exceptions.ParseException;
import vanya9090.client.models.Car;

import vanya9090.client.utils.ILogger;

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
    public Car create() throws EmptyFieldException, BooleanFormatException, ParseException {
        return new Car(this.askName(), this.askCool());
    }

    public String askName() throws EmptyFieldException {
        String name;
        while (true) {
            try {
                this.logger.field("Введите имя машины: ");
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

    public Boolean askCool() throws ParseException, EmptyFieldException {
        boolean cool;
        while (true) {
            try {
                this.logger.field("Машина хорошая?(true/false): ");
//                Scanner scanner = new Scanner(System.in);
                String field = this.scanner.nextLine().trim();
                if (field.isEmpty()) throw new EmptyFieldException("зубочистка");
                if (!field.equals("true") && !field.equals("false")) throw new ParseException("carCool", field);
                try {
                    cool = Boolean.parseBoolean(field);
                } catch (IllegalArgumentException e) {
                    throw new ParseException("cool", field);
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
        return cool;
    }
}
