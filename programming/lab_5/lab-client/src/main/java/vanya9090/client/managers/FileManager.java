package vanya9090.client.managers;


import com.google.gson.JsonSyntaxException;
import vanya9090.client.models.HumanBeing;
import vanya9090.common.exceptions.*;

import java.util.Collection;

/**
 * интерфейс файлового менеджера
 *
 * @author vanya9090
 */
public interface FileManager {
    public Collection<HumanBeing> readFile(String name) throws ValidateException, JsonSyntaxException, EmptyFileException, NotFoundException, AccessException, FormatException;

    public boolean writeFile(Collection<HumanBeing> collection, String name) throws NotFoundException, AccessException;
}