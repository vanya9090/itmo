package com.vanya9090.client.models.forms;

import com.vanya9090.client.exceptions.EmptyFieldException;
import com.vanya9090.client.exceptions.ParseException;
import com.vanya9090.client.exceptions.WrongFieldsException;

public interface Form {
    Object create() throws WrongFieldsException, ParseException, EmptyFieldException;
}
