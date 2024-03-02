package com.vanya9090.client.managers;

import com.vanya9090.client.models.HumanBeing;

import java.util.Collection;

public interface FileManager {
    Collection<HumanBeing> readFile(String name);

    void writeFile(Collection<HumanBeing> collection, String name);
}
