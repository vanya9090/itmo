package vanya9090.common.exceptions;

public abstract class MessageException extends Exception{
    String message;
    public MessageException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
