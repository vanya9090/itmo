package com.vanya9090.client.models;

public enum Mood implements Validatable{
    SADNESS,
    SORROW,
    APATHY,
    CALM,
    RAGE;

    @Override
    public boolean validate() {
        return true;
    }
}
