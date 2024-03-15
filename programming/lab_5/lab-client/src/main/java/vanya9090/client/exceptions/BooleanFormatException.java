package vanya9090.client.exceptions;

public class BooleanFormatException extends Exception {
    @Override
    public String toString() {
        return "Поле должно быть типа boolean(true/false)";
    }
}