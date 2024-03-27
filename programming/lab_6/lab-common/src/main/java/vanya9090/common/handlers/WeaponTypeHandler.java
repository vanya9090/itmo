//package vanya9090.common.handlers;
//
//import vanya9090.client.models.WeaponType;
//import vanya9090.common.exceptions.EmptyFieldException;
//import vanya9090.common.exceptions.ParseException;
//
//public class WeaponTypeHandler extends Handler<WeaponType> {
//    @Override
//    public WeaponType handle(String field, String fieldName) throws EmptyFieldException, ParseException {
//        if (field.isEmpty()) throw new EmptyFieldException(fieldName);
//        try {
//            return WeaponType.valueOf(field.toUpperCase());
//        } catch (IllegalArgumentException e) {
//            throw new ParseException(fieldName, field);
//        }
//    }
//}
