package vanya9090.server.handlers;

import vanya9090.common.exceptions.EmptyFieldException;
import vanya9090.common.exceptions.ParseException;
import vanya9090.server.models.WeaponType;

public class WeaponTypeHandler extends Handler<WeaponType> {
    @Override
    public WeaponType handle(String field, String fieldName) throws EmptyFieldException, ParseException {
        if (field.isEmpty()) throw new EmptyFieldException(fieldName);
        try {
            return WeaponType.valueOf(field.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ParseException(fieldName, field);
        }
    }
}
