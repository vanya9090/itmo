package com.vanya9090.client.managers;

import com.google.gson.JsonArray;
import com.vanya9090.client.exceptions.ReadException;
import com.vanya9090.client.exceptions.WrongPathException;
import com.vanya9090.client.models.HumanBeing;

import java.util.Collection;

public interface FileManager {
    JsonArray readFile(String name) throws ReadException, WrongPathException;

    void writeFile(Collection<HumanBeing> collection, String name);
}
