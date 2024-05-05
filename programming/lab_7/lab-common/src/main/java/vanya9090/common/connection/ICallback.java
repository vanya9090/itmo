package vanya9090.common.connection;

@FunctionalInterface
public interface ICallback<T> {
    Response call(T values) throws Exception;
}