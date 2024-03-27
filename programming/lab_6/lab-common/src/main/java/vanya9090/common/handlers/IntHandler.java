package vanya9090.common.handlers;

import vanya9090.common.exceptions.EmptyFieldException;
import vanya9090.common.exceptions.ParseException;

public class IntHandler extends Handler<Integer>{
    @Override
    public Integer handle(String field, String fieldName) throws EmptyFieldException, ParseException {
        if (field.isEmpty()) throw new EmptyFieldException(fieldName);
        try {
            return Integer.parseInt(field);
        } catch (NumberFormatException e) {
            throw new ParseException(fieldName, field);
        }
    }
}
