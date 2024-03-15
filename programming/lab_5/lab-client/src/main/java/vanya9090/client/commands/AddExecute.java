package vanya9090.client.commands;


import vanya9090.client.exceptions.BooleanFormatException;
import vanya9090.client.exceptions.EmptyFieldException;
import vanya9090.client.exceptions.ParseException;
import vanya9090.client.managers.CollectionManager;
import vanya9090.client.models.HumanBeing;
import vanya9090.client.models.forms.HumanBeingForm;
import vanya9090.client.utils.ILogger;

import java.util.Scanner;

public class AddExecute extends Command {
    private final ILogger logger;
    private final CollectionManager collectionManager;

    public AddExecute(ILogger logger, CollectionManager collectionManager) {
        super("add_execute", "добавить новый элемент в коллекцию");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }

    public void apply(String[] args, Scanner fileReader) {
        try {
            HumanBeing.updateNextId(collectionManager);
            HumanBeingForm humanBeingForm = new HumanBeingForm(this.logger, fileReader, true);
            HumanBeing humanBeing = humanBeingForm.create();
            collectionManager.add(humanBeing);
            logger.success("Добавлено успешно");
        } catch (BooleanFormatException | ParseException | EmptyFieldException e) {
            logger.error(e);
        }
    }

    @Override
    public void apply(String[] args) {
        logger.error("произошла непредвиденная ошибка");
    }
}
