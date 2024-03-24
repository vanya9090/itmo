package vanya9090.server.handlers;


import vanya9090.common.exceptions.EmptyFieldException;
import vanya9090.common.exceptions.ParseException;

/**
 * handle and natural throws
 * @author vanya9090
 * @param <T>
 */
public abstract class Handler<T> {
    public abstract T handle(String field, String fieldName) throws EmptyFieldException, ParseException;
}
