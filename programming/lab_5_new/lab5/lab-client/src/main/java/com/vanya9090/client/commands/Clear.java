package com.vanya9090.client.commands;

import com.vanya9090.client.exceptions.CollectionIsEmptyException;
import com.vanya9090.client.managers.CollectionManager;
import com.vanya9090.client.utils.ILogger;
import com.vanya9090.client.utils.Logger;

public class Clear extends Command {
    private final ILogger logger;
    private final CollectionManager collectionManager;

    public Clear(ILogger logger, CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }

    @Override
    public void apply(String[] args) {
        try {
            if (collectionManager.getSize() == 0) {
                throw new CollectionIsEmptyException();
            }
            collectionManager.clear();
            logger.info("Очищено успешно");
        } catch (CollectionIsEmptyException e) {
            logger.error("коллекция пуста");
        }
    }
}
