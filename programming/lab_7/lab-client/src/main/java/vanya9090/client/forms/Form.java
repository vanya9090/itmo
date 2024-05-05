package vanya9090.client.forms;

import vanya9090.common.exceptions.EmptyFieldException;
import vanya9090.common.exceptions.ParseException;

/**
 * интерфейс формы
 *
 * @author vanya9090
 */
public interface Form {
    Object create() throws ParseException, EmptyFieldException;
}
