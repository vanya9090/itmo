package com.vanya9090.client.models.forms;

import com.vanya9090.client.exceptions.EmptyFieldException;
import com.vanya9090.client.exceptions.WrongValueException;
import com.vanya9090.client.models.Coordinates;
import com.vanya9090.client.utils.Logger;

import java.util.Scanner;

public class CoordinatesForm implements Form {
    private final static int LOWER_BOUND = 925;
    private final static int UPPER_BOUND = -208;
    private final Logger logger;
    public CoordinatesForm(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Coordinates create() {
        return new Coordinates(this.askX(), this.askY());
    }

    public Integer askX() {
        int x;
        while (true) {
            try {
                this.logger.field("Введите x: ");
                Scanner scanner = new Scanner(System.in);
                String field = scanner.nextLine().trim();
                if (field.isEmpty()) {
                    throw new EmptyFieldException("координата x");
                }
                x = Integer.parseInt(field);
                if (x > LOWER_BOUND) {
                    throw new WrongValueException();
                }
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

    public Float askY() {
        float y;
        while (true) {
            try {
                this.logger.field("Введите y: ");
                Scanner scanner = new Scanner(System.in);
                String field = scanner.nextLine().trim();
                if (field.isEmpty()) {
                    throw new EmptyFieldException("координата y");
                }
                y = Float.parseFloat(field);
                if (y < UPPER_BOUND) {
                    throw new WrongValueException();
                }
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
