package exceptions;

public class EmptyFieldException extends Exception{
    private final String fieldName;
    public EmptyFieldException(String fieldName){
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        return "Пустое поле: " + this.fieldName;
    }
}
