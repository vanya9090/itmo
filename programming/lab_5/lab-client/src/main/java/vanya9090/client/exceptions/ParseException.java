package vanya9090.client.exceptions;

public class ParseException extends Exception {
    String fieldName;
    String field;

    public ParseException(String fieldName, String field) {
        this.fieldName = fieldName;
        this.field = field;
    }

    @Override
    public String toString() {
        return "неверный формат поля " + this.fieldName + ": " + this.field;
    }
}
