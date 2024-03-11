package vanya9090.common.Handlers;

import vanya9090.common.exceptions.EmptyFieldException;

public class IntHandler extends Handler<Integer>{
    @Override
    public Integer handle(String field) throws EmptyFieldException, NumberFormatException {
        if (field.isEmpty()) throw new EmptyFieldException();
        return Integer.parseInt(field);
    }
}
