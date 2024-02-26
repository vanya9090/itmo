package managers;

import models.HumanBeing;

import java.util.ArrayDeque;
import java.util.Collection;

public interface FileManager {
    public Collection<HumanBeing> readFile(String name);

    public void writeFile(Collection<HumanBeing> collection, String name);
}