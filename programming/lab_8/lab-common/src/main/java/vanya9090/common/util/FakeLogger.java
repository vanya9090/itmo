package vanya9090.common.util;

public class FakeLogger implements ILogger{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    @Override
    public void info(Object obj) {

    }

    @Override
    public void success(Object obj) {

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
    public void field(Object obj) {

    }
}
