package com.vanya9090.client.validators;

import com.vanya9090.client.exceptions.EmptyFieldException;
import com.vanya9090.client.models.Mood;

public abstract class Validator {
    public abstract boolean validate(String field);
    public abstract Mood validateHandle(String field) throws EmptyFieldException;

}
