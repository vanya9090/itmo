package com.vanya9090.client.handlers;

import com.vanya9090.client.exceptions.EmptyFieldException;
import com.vanya9090.client.exceptions.ParseException;

/**
 * handle and natural throws
 * @author vanya9090
 * @param <T>
 */
public abstract class Handler<T> {
    public abstract T handle(String field, String fieldName) throws EmptyFieldException, ParseException;
}
