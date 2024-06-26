package vanya9090.common.validators;

import java.time.LocalDate;

public class CreationDateValidator extends Validator<LocalDate>{
    @Override
    public boolean validate(LocalDate field) {
        if (field.isAfter(LocalDate.now())) return false;
        return true;
    }
}
