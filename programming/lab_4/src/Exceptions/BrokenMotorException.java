package Exceptions;

public class BrokenMotorException extends Exception{
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public BrokenMotorException(String message){
        super(message);
        System.out.println(ANSI_RED + "Ошибка: " + message + ANSI_RESET);
    }
}
