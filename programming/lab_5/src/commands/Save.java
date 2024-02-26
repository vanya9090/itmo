package commands;

import managers.CollectionManager;
import managers.FileManager;
import utils.Logger;

public class Save extends Command{
    private final Logger logger;
    private final CollectionManager collectionManager;
    private final FileManager fileManager;

    public Save(Logger logger, CollectionManager collectionManager, FileManager fileManager) {
        super("save", "сохранить коллекцию в файл");
        this.logger = logger;
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }
    @Override
    public void apply(String[] args) {
        this.fileManager.writeFile(collectionManager.getCollection(), "src/file.json");
    }
}
