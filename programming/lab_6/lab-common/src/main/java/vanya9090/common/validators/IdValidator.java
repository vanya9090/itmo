package vanya9090.common.validators;

public class IdValidator extends Validator<Integer>{

    @Override
    public boolean validate(Integer field) {
        if (field < 0) return false;
        return true;
    }
}
