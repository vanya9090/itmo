package vanya9090.server.managers;


import com.google.gson.JsonArray;
import com.google.gson.JsonSyntaxException;
import vanya9090.common.models.HumanBeing;
import vanya9090.common.exceptions.*;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Objects;

/**
 * интерфейс файлового менеджера
 *
 * @author vanya9090
 */
public interface FileManager {
    Object readFile(String name) throws Exception;

    void writeFile(Collection<HumanBeing> collection, String name) throws Exception;
}