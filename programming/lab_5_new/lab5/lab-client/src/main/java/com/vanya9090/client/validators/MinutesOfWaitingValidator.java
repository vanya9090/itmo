package com.vanya9090.client.validators;

public class MinutesOfWaitingValidator extends Validator<Float>{
    @Override
    public boolean validate(Float field) {
        if (field < 0) return false;
        return true;
    }
}
