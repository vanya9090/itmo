package vanya9090.server.validators;

import java.time.LocalDate;

public class CreationDateValidator extends Validator<LocalDate>{
    @Override
    public boolean validate(LocalDate field) {
        if (field.isAfter(LocalDate.now())) return false;
        return true;
    }
}
