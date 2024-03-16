package vanya9090.client.utils;

/**
 * интерфейс логера
 *
 * @author vanya9090
 */
public interface ILogger {
    void info(Object obj);

    void success(Object obj);

    void warning(Object obj);

    void error(Object obj);

    void table(Object obj1, Object obj2);

    void field(Object obj);
}
