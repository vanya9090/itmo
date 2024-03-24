package vanya9090.server.managers;


import com.google.gson.JsonArray;
import com.google.gson.JsonSyntaxException;
import vanya9090.client.models.HumanBeing;
import vanya9090.common.exceptions.*;

import java.io.FileNotFoundException;
import java.util.Collection;

/**
 * интерфейс файлового менеджера
 *
 * @author vanya9090
 */
public interface FileManager {
    JsonArray readFile(String name) throws ValidateException, JsonSyntaxException, EmptyFileException, NotFoundException, AccessException, FormatException, FileNotFoundException;

    void writeFile(Collection<HumanBeing> collection, String name) throws NotFoundException, AccessException;
}