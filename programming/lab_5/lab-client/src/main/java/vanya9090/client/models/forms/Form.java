package vanya9090.client.models.forms;

import vanya9090.common.exceptions.*;

public interface Form {
    public Object create() throws ParseException, EmptyFieldException, BooleanFormatException;
}
