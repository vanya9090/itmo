package vanya9090.client.commands;

import vanya9090.client.managers.CollectionManager;
import vanya9090.client.managers.FileManager;
import vanya9090.common.exceptions.AccessException;
import vanya9090.common.exceptions.NotFoundException;

public class Save extends Command {
    private final CollectionManager collectionManager;
    private final FileManager fileManager;

    public Save(CollectionManager collectionManager, FileManager fileManager) {
        super("save", "сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }

    @Override
    public String apply(String[] args) throws AccessException, NotFoundException {
        this.fileManager.writeFile(collectionManager.getCollection(), args[0]);
        return "";
    }
}
