package vanya9090.common.Validators;

public abstract class Validator<T> {
    public abstract boolean validate(String field);
    public abstract T handle(String field);
}
