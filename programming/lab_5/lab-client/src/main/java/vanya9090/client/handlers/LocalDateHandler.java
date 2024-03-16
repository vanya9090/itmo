package vanya9090.client.handlers;

import vanya9090.common.exceptions.EmptyFieldException;
import vanya9090.common.exceptions.ParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateHandler extends Handler<LocalDate> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Override
    public LocalDate handle(String field, String fieldName) throws EmptyFieldException, ParseException {
        if (field.isEmpty()) throw new EmptyFieldException(fieldName);
        try {
            return LocalDate.parse(field, formatter);
        } catch (DateTimeParseException e) {
            throw new ParseException(fieldName, field);
        }
    }
}
