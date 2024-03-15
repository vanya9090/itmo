package vanya9090.client.commands;


import vanya9090.client.exceptions.BooleanFormatException;
import vanya9090.client.exceptions.EmptyFieldException;
import vanya9090.client.exceptions.ParseException;
import vanya9090.client.exceptions.ValidateException;
import vanya9090.client.managers.CollectionManager;
import vanya9090.client.models.HumanBeing;
import vanya9090.client.models.forms.HumanBeingForm;
import vanya9090.client.utils.ILogger;

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
        HumanBeing.updateNextId(collectionManager);
        HumanBeingForm humanBeingForm = new HumanBeingForm(this.logger, new Scanner(System.in), false);
        try {
            HumanBeing humanBeing = humanBeingForm.create();
            if (!humanBeing.validate()) {
                throw new ValidateException();
            }
            collectionManager.add(humanBeing);
            logger.success("Добавлено успешно");
        } catch (ValidateException | BooleanFormatException | ParseException | EmptyFieldException e) {
            logger.error("непредвиденная ошибка");
        }
    }
}
