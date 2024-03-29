package vanya9090.common.handlers;

import vanya9090.common.models.Mood;
import vanya9090.common.exceptions.EmptyFieldException;
import vanya9090.common.exceptions.ParseException;

public class MoodHandler extends Handler<Mood> {
    @Override
    public Mood handle(String field, String fieldName) throws EmptyFieldException, ParseException {
        if (field.isEmpty()) throw new EmptyFieldException(fieldName);
        try {
            return Mood.valueOf(field.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ParseException(fieldName, field);
        }
    }
}
