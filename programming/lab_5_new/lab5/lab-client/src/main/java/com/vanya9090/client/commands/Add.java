package com.vanya9090.client.commands;

import com.vanya9090.client.exceptions.EmptyFieldException;
import com.vanya9090.client.exceptions.ParseException;
import com.vanya9090.client.exceptions.WrongFieldsException;
import com.vanya9090.client.managers.CollectionManager;
import com.vanya9090.client.models.HumanBeing;
import com.vanya9090.client.models.forms.HumanBeingForm;
import com.vanya9090.client.utils.ILogger;
import com.vanya9090.client.utils.Logger;

import java.util.Scanner;

public class Add extends Command {
    private final ILogger logger;
    private final CollectionManager collectionManager;

    public Add(ILogger logger, CollectionManager collectionManager) {
        super("add", "добавить новый элемент в коллекцию");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }

    @Override
    public void apply(String[] args) {
        try {
            HumanBeing.updateNextId(collectionManager);
            HumanBeingForm humanBeingForm = new HumanBeingForm(this.logger, new Scanner(System.in), false);
            HumanBeing humanBeing = humanBeingForm.create();
            collectionManager.add(humanBeing);
            logger.success("Добавлено успешно");
        } catch (ParseException | EmptyFieldException | WrongFieldsException e) {
            logger.error("непредвиденная ошибка");
        }
    }
}
