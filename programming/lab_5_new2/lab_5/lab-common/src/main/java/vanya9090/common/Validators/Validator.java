package vanya9090.common.Validators;

/**
 * check and synthetic throws
 * @author vanya9090
 * @param <T>
 */
public abstract class Validator<T> {
    public abstract boolean validate(String field);
    public abstract T handle(String field);
}
