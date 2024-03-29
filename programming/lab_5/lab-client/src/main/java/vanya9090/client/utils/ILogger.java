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

    void field(Object obj);
}
