package models.forms;

import exceptions.BooleanFormatException;
import exceptions.EmptyFieldException;
import models.Car;
import utils.Logger;

import java.util.Scanner;

public class CarForm implements Form {
    private final Logger logger;

    public CarForm(Logger logger){
        this.logger = logger;
    }
    @Override
    public Car create() {
        return new Car(this.askName(), this.askCool());
    }

    public String askName(){
        String name;
        while (true){
            try {
                this.logger.field("Введите имя машины: ");
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

    public Boolean askCool(){
        boolean cool;
        while (true){
            try {
                this.logger.field("Машина хорошая?: ");
                Scanner scanner = new Scanner(System.in);
                String field = scanner.nextLine().trim();
                if (field.isEmpty()) throw new EmptyFieldException("зубочистка");
                if (!field.equals("true") && !field.equals("false")) throw new BooleanFormatException();
                cool = Boolean.parseBoolean(field);
                break;
            } catch (BooleanFormatException | EmptyFieldException e) {
                logger.error(e);
            }
        }
        return cool;
    }
}
