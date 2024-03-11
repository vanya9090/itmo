package vanya9090.server;

public class Logger {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public void info(Object obj) {
        System.out.println(obj);
    }

    public void success(Object obj) {
        System.out.println(obj);
    }

    public void warning(Object obj) {
        System.out.println(ANSI_YELLOW + obj + ANSI_RESET);
    }

    public void error(Object obj) {
        System.out.println(ANSI_RED + obj + ANSI_RESET);
    }

    public void table(Object obj1, Object obj2) {
        System.out.printf("%-36s%s%n", obj1, obj2);
    }

    public void field(Object obj) {
        System.out.print(obj);
    }
}
