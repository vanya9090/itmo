package com.vanya9090.client.models;

public enum WeaponType implements Validatable{
    HAMMER,
    AXE,
    SHOTGUN,
    RIFLE,
    KNIFE;

    @Override
    public boolean validate() {
        return true;
    }
}
