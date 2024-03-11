package com.vanya9090.client.exceptions;

public class NullFieldException extends Exception{
    private final String field;
    public NullFieldException(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "поле " + this.field + " не может быть null";
    }
}
