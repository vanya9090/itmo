package com.vanya9090.client.handlers;

import com.vanya9090.client.exceptions.EmptyFieldException;

public class StringHandler extends Handler<String>{
    @Override
    public String handle(String field, String fieldName) throws EmptyFieldException {
        if (field.isEmpty()) throw new EmptyFieldException(fieldName);
        return field;
    }
}
