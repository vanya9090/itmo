package com.vanya9090.client.exceptions;

public class WrongFieldsException extends Exception{
    private final int id;
    private final String fieldName;
    public WrongFieldsException(int id, String fieldName){
        this.id = id;
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        return "человек с id=" + this.id + " имеет невалидное поле " + this.fieldName;
    }
}
