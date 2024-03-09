package vanya9090.common.Validators;

import vanya9090.common.exceptions.EmptyFieldException;

public class IdValidator extends Validator<Integer>{
    @Override
    public boolean validate(String field) {
        return true;
    }

    @Override
    public Integer handle(String field) {
        try {
            if (field.isEmpty()) throw new EmptyFieldException();
            return Integer.parseInt(field);
        } catch (NumberFormatException e) {
            System.out.println("some text1");
        } catch (EmptyFieldException e) {
            System.out.println("some text");
        }
        return null;
    }
}
