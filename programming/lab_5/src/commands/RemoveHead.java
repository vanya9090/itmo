package commands;

import managers.CollectionManager;
import utils.Logger;

public class RemoveHead extends Command{
    private final Logger logger;
    private final CollectionManager collectionManager;
    public RemoveHead(Logger logger, CollectionManager collectionManager) {
        super("remove_head", "вывести первый элемент коллекции и удалить его");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }

    @Override
    public void apply(String[] args) {
        logger.info("первый элемент удален:");
        logger.info(collectionManager.removeHead());
    }
}
