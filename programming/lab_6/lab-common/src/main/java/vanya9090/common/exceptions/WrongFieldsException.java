package vanya9090.common.exceptions;

public class WrongFieldsException extends Exception {
    private final int id;
    private final String fieldName;

    public WrongFieldsException(int id, String fieldName) {
        this.id = id;
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        return "синтетически невалидное поле " + this.fieldName;
    }
}
