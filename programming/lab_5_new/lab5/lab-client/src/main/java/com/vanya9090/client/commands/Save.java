package com.vanya9090.client.commands;

import com.vanya9090.client.managers.CollectionManager;
import com.vanya9090.client.managers.FileManager;

public class Save extends Command {
    private final CollectionManager collectionManager;
    private final FileManager fileManager;
    private final String envKey;

    public Save(CollectionManager collectionManager, FileManager fileManager, String envKey) {
        super("save", "сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
        this.envKey = envKey;
    }

    @Override
    public void apply(String[] args) {
        this.fileManager.writeFile(collectionManager.getCollection(), this.envKey);
    }
}
