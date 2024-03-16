package vanya9090.client.validators;

/**
 * check and synthetic throws
 * @author vanya9090
 * @param <T>
 */
public abstract class Validator<T> {
    public abstract boolean validate(T field);
}
