package vanya9090.common.validators;

public class MinutesOfWaitingValidator extends Validator<Float>{
    @Override
    public boolean validate(Float field) {
        if (field < 0) return false;
        return true;
    }
}
