package commands;

import managers.CollectionManager;
import utils.Logger;

public class RemoveFirst extends Command{
    private final Logger logger;
    private final CollectionManager collectionManager;

    public RemoveFirst(Logger logger, CollectionManager collectionManager) {
        super("remove_first", "удалить первый элемент из коллекции");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }

    @Override
    public void apply(String[] args) {
        this.collectionManager.removeFirst();
        logger.info("первый элемент удален");
    }
}
