package vanya9090.common.util;


/**
 * класс для вывода в консоль
 *
 * @author vanya9090
 */
public class Logger implements ILogger {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";

    @Override
    public void info(Object obj) {
        System.out.println(obj);
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

//    @Override
//    public void table(Object obj1, Object obj2) {
//        System.out.printf("%-36s%s%n", obj1, obj2);
//    }

    @Override
    public void field(Object obj) {
        System.out.print(obj);
    }
}
