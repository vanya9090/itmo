package vanya9090.common.Handlers;

import vanya9090.common.exceptions.EmptyFieldException;

/**
 * handle and natural throws
 * @author vanya9090
 * @param <T>
 */
public abstract class Handler<T> {
    public abstract T handle(String field) throws NumberFormatException, EmptyFieldException;
}
