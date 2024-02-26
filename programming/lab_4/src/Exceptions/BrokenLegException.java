package Exceptions;

public class BrokenLegException extends RuntimeException{
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public BrokenLegException(String message){
        super(message);
        System.out.println(ANSI_RED + "Ошибка: " + message + ANSI_RESET);
    }
}
