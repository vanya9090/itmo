package com.vanya9090.client.validators;

import com.vanya9090.client.exceptions.WrongValueException;

public class XValidator extends Validator<Integer>{
    private final static int LOWER_BOUND = 925;
    @Override
    public boolean validate(Integer field) {
        if (field > LOWER_BOUND) return false;
        return true;
    }
}
