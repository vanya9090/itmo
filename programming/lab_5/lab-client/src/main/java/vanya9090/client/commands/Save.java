package vanya9090.client.commands;

import vanya9090.client.managers.CollectionManager;
import vanya9090.client.managers.FileManager;

public class Save extends Command {
    private final CollectionManager collectionManager;
    private final FileManager fileManager;

    public Save(CollectionManager collectionManager, FileManager fileManager) {
        super("save", "сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }

    @Override
    public void apply(String[] args) {
        this.fileManager.writeFile(collectionManager.getCollection(), "src/file.json");
    }
}
