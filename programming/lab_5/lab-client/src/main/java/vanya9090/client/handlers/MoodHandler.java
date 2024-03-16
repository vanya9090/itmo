package vanya9090.client.handlers;

import vanya9090.common.exceptions.EmptyFieldException;
import vanya9090.common.exceptions.ParseException;
import vanya9090.client.models.Mood;

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
