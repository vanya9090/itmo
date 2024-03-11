package com.vanya9090.client.commands;

import com.vanya9090.client.managers.CollectionManager;
import com.vanya9090.client.models.HumanBeing;
import com.vanya9090.client.models.forms.HumanBeingForm;
import com.vanya9090.client.utils.ILogger;
import com.vanya9090.client.utils.Logger;

import java.util.Scanner;

public class AddExecute extends Command{
    private final ILogger logger;
    private final CollectionManager collectionManager;

    public AddExecute(ILogger logger, CollectionManager collectionManager) {
        super("addExecute", "добавить новый элемент в коллекцию");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }

    public void apply(String[] args, Scanner fileReader) {
        HumanBeing.updateNextId(collectionManager);
        HumanBeingForm humanBeingForm = new HumanBeingForm(this.logger, fileReader);
        HumanBeing humanBeing = humanBeingForm.create();
        collectionManager.add(humanBeing);
        logger.success("Добавлено успешно");
    }

    @Override
    public void apply(String[] args) {
        logger.error("произошла непредвиденная ошибка");
    }
}
