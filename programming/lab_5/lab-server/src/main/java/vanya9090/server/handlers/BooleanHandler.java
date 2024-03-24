package vanya9090.server.handlers;


import vanya9090.common.exceptions.EmptyFieldException;
import vanya9090.common.exceptions.ParseException;

public class BooleanHandler extends Handler<Boolean>{
    @Override
    public Boolean handle(String field, String fieldName) throws EmptyFieldException, ParseException {

        if (field.isEmpty()) throw new EmptyFieldException(fieldName);
        if (!"true".equals(field) && !"false".equals(field)) throw new ParseException(fieldName, field);
        return Boolean.parseBoolean(field);
    }
}
