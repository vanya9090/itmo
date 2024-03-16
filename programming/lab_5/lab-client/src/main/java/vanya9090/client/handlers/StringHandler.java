package vanya9090.client.handlers;

import vanya9090.common.exceptions.EmptyFieldException;

public class StringHandler extends Handler<String>{
    @Override
    public String handle(String field, String fieldName) throws EmptyFieldException {
        if (field.isEmpty()) throw new EmptyFieldException(fieldName);
        return field;
    }
}
