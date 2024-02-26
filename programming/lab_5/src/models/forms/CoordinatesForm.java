package models.forms;

import exceptions.BooleanFormatException;
import exceptions.EmptyFieldException;
import exceptions.WrongValueException;
import models.Car;
import models.Coordinates;
import utils.Logger;

import java.nio.charset.CoderResult;
import java.util.Scanner;

public class CoordinatesForm implements Form {
    private final Logger logger;

    public CoordinatesForm(Logger logger){
        this.logger = logger;
    }
    @Override
    public Coordinates create() {
        return new Coordinates(this.askX(), this.askY());
    }

    public Integer askX(){
        int x;
        while (true){
            try {
                this.logger.field("Введите x: ");
                Scanner scanner = new Scanner(System.in);
                String field = scanner.nextLine().trim();
                if (field.isEmpty()) throw new EmptyFieldException("координата x");
                x = Integer.parseInt(field);
                if (x > 925) throw new WrongValueException();
                break;
            } catch (EmptyFieldException e) {
                logger.error(e);
            } catch (NumberFormatException e) {
                logger.error("Координата x должна быть представлена целым числом");
            } catch (WrongValueException e) {
                logger.error("Координата x должна быть меньше 926");
            }
        }
        return x;
    }

    public Float askY(){
        float y;
        while (true){
            try {
                this.logger.field("Введите y: ");
                Scanner scanner = new Scanner(System.in);
                String field = scanner.nextLine().trim();
                if (field.isEmpty()) throw new EmptyFieldException("координата y");
                y = Float.parseFloat(field);
                if (y < -208) throw new WrongValueException();
                break;
            } catch (EmptyFieldException e) {
                logger.error(e);
            } catch (NumberFormatException e) {
                logger.error("Координата y должна быть представлена числом");
            } catch (WrongValueException e) {
                logger.error("Координата y должна быть больше -208");
            }
        }
        return y;
    }
}
