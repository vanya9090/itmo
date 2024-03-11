package com.vanya9090.client.commands;

import com.vanya9090.client.exceptions.EmptyCollectionException;
import com.vanya9090.client.managers.CollectionManager;
import com.vanya9090.client.utils.ILogger;
import com.vanya9090.client.utils.Logger;

public class RemoveFirst extends Command {
    private final ILogger logger;
    private final CollectionManager collectionManager;

    public RemoveFirst(ILogger logger, CollectionManager collectionManager) {
        super("remove_first", "удалить первый элемент из коллекции");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }

    @Override
    public void apply(String[] args) {
        try {
            if (this.collectionManager.getSize() == 0) {
                throw new EmptyCollectionException();
            }
            this.collectionManager.removeFirst();
            logger.info("первый элемент удален");
        } catch (EmptyCollectionException e) {
            logger.error(e);
        }
    }
}
