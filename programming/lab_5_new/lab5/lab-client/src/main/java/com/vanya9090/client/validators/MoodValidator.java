package com.vanya9090.client.validators;

import com.vanya9090.client.exceptions.EmptyFieldException;
import com.vanya9090.client.models.Mood;

public class MoodValidator extends Validator{
    @Override
    public boolean validate(String field) {

        return false;
    }

    @Override
    public Mood validateHandle(String field) throws EmptyFieldException, IllegalArgumentException {
        if (field.isEmpty()) {
            throw new EmptyFieldException("настроение");
        }
        return Mood.valueOf(field.toUpperCase());
    }
}
