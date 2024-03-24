package vanya9090.server.validators;

public class ImpactSpeedValidator extends Validator<Integer>{
    @Override
    public boolean validate(Integer field) {
        if (field < 0) return false;
        return true;
    }
}
