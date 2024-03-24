package vanya9090.server.validators;

import vanya9090.server.models.Mood;

public class MoodValidator extends Validator<Mood>{
    @Override
    public boolean validate(Mood field) {
        return true;
    }
}
