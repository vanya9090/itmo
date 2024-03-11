package com.vanya9090.client.handlers;

import com.vanya9090.client.exceptions.EmptyFieldException;
import com.vanya9090.client.exceptions.ParseException;

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
