package com.vanya9090.client.commands;

import com.vanya9090.client.exceptions.EmptyCollectionException;
import com.vanya9090.client.managers.CollectionManager;
import com.vanya9090.client.models.HumanBeing;
import com.vanya9090.client.utils.Logger;

public class Show extends Command {
    private final CollectionManager collectionManager;
    private final Logger logger;

    public Show(Logger logger, CollectionManager collectionManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
        this.logger = logger;
    }

    @Override
    public void apply(String[] args) {
        try {
            if (collectionManager.getCollection().isEmpty()) {
                throw new EmptyCollectionException();
            }
            for (HumanBeing human : collectionManager.getCollection()) {
                logger.info(human);
                logger.info("");
            }
        } catch (EmptyCollectionException e) {
            logger.error(e);
        }
    }
}
