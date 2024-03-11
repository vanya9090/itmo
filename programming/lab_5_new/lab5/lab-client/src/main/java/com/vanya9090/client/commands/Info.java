package com.vanya9090.client.commands;

import com.vanya9090.client.exceptions.EmptyCollectionException;
import com.vanya9090.client.managers.CollectionManager;
import com.vanya9090.client.utils.ILogger;
import com.vanya9090.client.utils.Logger;

import java.time.format.DateTimeFormatter;

public class Info extends Command {
    private final ILogger logger;
    private final CollectionManager collectionManager;

    public Info(ILogger logger, CollectionManager collectionManager) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }

    @Override
    public void apply(String[] args) {
        try {
            if (collectionManager.getCollection().isEmpty()) {
                throw new EmptyCollectionException();
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            this.logger.info("Тип: " + this.collectionManager.getType());
            this.logger.info("Дата инициализации: " + dtf.format(this.collectionManager.getInitDate()));
            this.logger.info("Количество элементов: " + this.collectionManager.getSize());
        } catch (EmptyCollectionException e) {
            logger.warning(e);
        }
    }
}
