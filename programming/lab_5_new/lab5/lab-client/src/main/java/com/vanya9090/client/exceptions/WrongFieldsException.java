package com.vanya9090.client.exceptions;

public class WrongFieldsException extends Exception{
    private final int id;
    public WrongFieldsException(int id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "Человек с id=" + this.id + " имеет невалидные поля";
    }
}
