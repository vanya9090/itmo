package com.vanya9090.client.validators;

import com.vanya9090.client.models.Mood;

public class MoodValidator extends Validator<Mood>{
    @Override
    public boolean validate(Mood field) {
        return true;
    }
}
