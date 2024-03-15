package vanya9090.client.models.forms;

import vanya9090.client.exceptions.*;

public interface Form {
    public Object create() throws ParseException, EmptyFieldException, BooleanFormatException;
}
