package vanya9090.client.utils;

/**
 * логер для работы со скриптом
 *
 * @author vanya9090
 */
public class ExecuteLogger implements ILogger {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    @Override
    public void info(Object obj) {

    }

    @Override
    public void success(Object obj) {
        System.out.println(ANSI_GREEN + obj + ANSI_RESET);
    }

    @Override
    public void warning(Object obj) {
        System.out.println(ANSI_YELLOW + obj + ANSI_RESET);
    }

    @Override
    public void error(Object obj) {
        System.out.println(ANSI_RED + obj + ANSI_RESET);
    }

    @Override
    public void table(Object obj1, Object obj2) {

    }

    @Override
    public void field(Object obj) {

    }
}
