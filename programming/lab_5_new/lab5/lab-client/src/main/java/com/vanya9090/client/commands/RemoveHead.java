package com.vanya9090.client.commands;

import com.vanya9090.client.exceptions.EmptyCollectionException;
import com.vanya9090.client.managers.CollectionManager;
import com.vanya9090.client.utils.Logger;

public class RemoveHead extends Command {
    private final Logger logger;
    private final CollectionManager collectionManager;

    public RemoveHead(Logger logger, CollectionManager collectionManager) {
        super("remove_head", "вывести первый элемент коллекции и удалить его");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }

    @Override
    public void apply(String[] args) {
        try {
            if (this.collectionManager.getSize() == 0) {
                throw new EmptyCollectionException();
            }
            logger.info("первый элемент удален:");
            logger.info(collectionManager.removeHead());
        } catch (EmptyCollectionException e) {
            logger.error(e);
        }
    }
}
