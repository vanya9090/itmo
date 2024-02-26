package commands;

import managers.CollectionManager;
import utils.Logger;

public class Clear extends Command{
    private final Logger logger;
    private final CollectionManager collectionManager;

    public Clear(Logger logger, CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }
    @Override
    public void apply(String[] args) {
        collectionManager.clear();
        logger.info("Очищено успешно");
    }
}
