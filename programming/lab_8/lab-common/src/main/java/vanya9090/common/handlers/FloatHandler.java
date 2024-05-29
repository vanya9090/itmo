package vanya9090.common.handlers;


import vanya9090.common.exceptions.EmptyFieldException;
import vanya9090.common.exceptions.ParseException;

public class FloatHandler extends Handler<Float>{
    @Override
    public Float handle(String field, String fieldName) throws EmptyFieldException, ParseException {
        if (field.isEmpty()) throw new EmptyFieldException(fieldName);
        try {
            return Float.parseFloat(field);
        } catch (NumberFormatException e) {
            throw new ParseException(fieldName, field);
        }
    }
}
