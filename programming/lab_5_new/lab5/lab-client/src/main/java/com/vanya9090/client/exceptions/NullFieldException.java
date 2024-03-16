package com.vanya9090.client.exceptions;

public class NullFieldException extends Exception{
    private final String field;
    private final int id;
    public NullFieldException(int id, String field) {
        this.field = field;
        this.id = id;
    }

    @Override
    public String toString() {
        return "поле " + this.field + " не может быть null в объекте с номером " + id;
    }
}
