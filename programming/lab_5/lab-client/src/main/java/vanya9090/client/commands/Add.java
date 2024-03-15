package vanya9090.client.commands;


import vanya9090.client.utils.ExecuteLogger;
import vanya9090.common.exceptions.BooleanFormatException;
import vanya9090.common.exceptions.EmptyFieldException;
import vanya9090.common.exceptions.ParseException;
import vanya9090.common.exceptions.ValidateException;
import vanya9090.client.managers.CollectionManager;
import vanya9090.client.models.HumanBeing;
import vanya9090.client.models.forms.HumanBeingForm;
import vanya9090.client.utils.ILogger;

import java.util.Scanner;

public class Add extends Command implements Executable{
    private final ILogger logger;
    private final CollectionManager collectionManager;

    public Add(ILogger logger, CollectionManager collectionManager) {
        super("add", "добавить новый элемент в коллекцию");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }

    @Override
    public void apply(String[] args) throws BooleanFormatException, ParseException, EmptyFieldException, ValidateException {
        HumanBeing.updateNextId(collectionManager);
        HumanBeingForm humanBeingForm = new HumanBeingForm(this.logger, new Scanner(System.in), false);
        HumanBeing humanBeing = humanBeingForm.create();
        if (!humanBeing.validate()) {
            throw new ValidateException("некоторые поля не соответствуют синтетическим ограничениям");
        }
        collectionManager.add(humanBeing);
    }
    public void apply(String[] args, Scanner fileReader) throws BooleanFormatException, ParseException, EmptyFieldException {
        HumanBeing.updateNextId(collectionManager);
        HumanBeingForm humanBeingForm = new HumanBeingForm(new ExecuteLogger(), fileReader, true);
        HumanBeing humanBeing = humanBeingForm.create();
        collectionManager.add(humanBeing);
    }
}
