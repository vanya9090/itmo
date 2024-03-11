package com.vanya9090.client.models.forms;

import com.vanya9090.client.exceptions.EmptyFieldException;
import com.vanya9090.client.exceptions.ParseException;
import com.vanya9090.client.exceptions.WrongFieldsException;
import com.vanya9090.client.exceptions.WrongValueException;
import com.vanya9090.client.handlers.FloatHandler;
import com.vanya9090.client.handlers.IntHandler;
import com.vanya9090.client.models.Coordinates;
import com.vanya9090.client.utils.Logger;
import com.vanya9090.client.validators.XValidator;
import com.vanya9090.client.validators.YValidator;

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
        IntHandler intHandler = new IntHandler();
        XValidator xValidator = new XValidator();
        while (true) {
            try {
                this.logger.field("Введите x: ");
                Scanner scanner = new Scanner(System.in);
                String field = scanner.nextLine().trim();
                x = intHandler.handle(field, "x");
                if (!xValidator.validate(x)) throw new WrongFieldsException(0, "x");
                break;
            } catch (ParseException | EmptyFieldException | WrongFieldsException e) {
                logger.error(e);
            }
        }
        return x;
    }

    public Float askY() {
        float y;
        FloatHandler floatHandler = new FloatHandler();
        YValidator yValidator = new YValidator();
        while (true) {
            try {
                this.logger.field("Введите y: ");
                Scanner scanner = new Scanner(System.in);
                String field = scanner.nextLine().trim();
                y = floatHandler.handle(field, "y");
                if (!yValidator.validate(y)) throw new WrongFieldsException(0, "y");
                break;
            } catch (ParseException | EmptyFieldException | WrongFieldsException e) {
                logger.error(e);
            }
        }
        return y;
    }
}
