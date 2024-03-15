package vanya9090.client.managers;


import com.google.gson.JsonSyntaxException;
import vanya9090.client.models.HumanBeing;
import vanya9090.common.exceptions.AccessException;
import vanya9090.common.exceptions.EmptyFileException;
import vanya9090.common.exceptions.NotFoundException;
import vanya9090.common.exceptions.ValidateException;

import java.util.Collection;

public interface FileManager {
    public Collection<HumanBeing> readFile(String name) throws ValidateException, JsonSyntaxException, EmptyFileException, NotFoundException, AccessException;

    public boolean writeFile(Collection<HumanBeing> collection, String name) throws NotFoundException, AccessException;
}