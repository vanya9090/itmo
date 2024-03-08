package com.vanya9090.client.validators;

import com.vanya9090.client.exceptions.EmptyFieldException;
import com.vanya9090.client.models.Mood;
import com.vanya9090.client.utils.Logger;

public class Validator {
    private final Logger logger;
    public Validator(Logger logger){
        this.logger = logger;
    }

    public void validateMood(String field){
        try {
            if (field.isEmpty()) throw new EmptyFieldException("настроение");
            Mood mood = Mood.valueOf(field.toUpperCase());
        } catch (EmptyFieldException e) {
            logger.error(e);
        }
    }
}
