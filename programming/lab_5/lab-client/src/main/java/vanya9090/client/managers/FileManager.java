package vanya9090.client.managers;


import vanya9090.client.models.HumanBeing;

import java.util.Collection;

public interface FileManager {
    public Collection<HumanBeing> readFile(String name);

    public void writeFile(Collection<HumanBeing> collection, String name);
}