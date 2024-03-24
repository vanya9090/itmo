package vanya9090.server.validators;

public class YValidator extends Validator<Float>{
    private final static int UPPER_BOUND = -208;

    @Override
    public boolean validate(Float field) {
        if (field < UPPER_BOUND) return false;
        return true;
    }
}
