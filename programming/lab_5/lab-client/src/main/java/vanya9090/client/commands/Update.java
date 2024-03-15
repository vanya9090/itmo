package vanya9090.client.commands;


import vanya9090.client.managers.CollectionManager;
import vanya9090.client.models.HumanBeing;
import vanya9090.client.models.forms.HumanBeingForm;
import vanya9090.client.utils.ILogger;
import vanya9090.common.exceptions.*;

import java.util.Arrays;
import java.util.Scanner;

public class Update extends Command implements Executable{
    private final CollectionManager collectionManager;
    private final ILogger logger;

    public Update(ILogger logger, CollectionManager collectionManager) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }

    public String apply(String[] args) throws Exception {
        try {
            if (args[1].isEmpty()) throw new WrongAmountOfElementsException("пустой аргумент, введите id");
            if (collectionManager.getSize() == 0) throw new CollectionIsEmptyException("коллекция пуста");

            Integer id = Integer.parseInt(args[1].trim());
            HumanBeing humanToUpdate = collectionManager.getById(id);
            if (humanToUpdate == null) throw new NotFoundException("человек с таким id не найден");

            HumanBeingForm humanBeingForm = new HumanBeingForm(this.logger, new Scanner(System.in), false);
            HumanBeing humanBeing = humanBeingForm.create();

            humanToUpdate.update(humanBeing);
            return "";

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new WrongAmountOfElementsException("пустой аргумент, введите id");
        } catch (NumberFormatException e) {
            throw new FormatException("id должен быть представлен целым числом");
        }
    }

    @Override
    public String apply(String[] args, Scanner fileReader) throws Exception {
        try {
            if (args[1].isEmpty()) throw new WrongAmountOfElementsException("пустой аргумент, введите id");
            if (collectionManager.getSize() == 0) throw new CollectionIsEmptyException("коллекция пуста");

            Integer id = Integer.parseInt(args[1].trim());
            HumanBeing humanToUpdate = collectionManager.getById(id);
            if (humanToUpdate == null) throw new NotFoundException("человек с таким id не найден");

            HumanBeingForm humanBeingForm = new HumanBeingForm(this.logger, fileReader, true);
            HumanBeing humanBeing = humanBeingForm.create();

            humanToUpdate.update(humanBeing);
            return "";

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new WrongAmountOfElementsException("пустой аргумент, введите id");
        } catch (NumberFormatException e) {
            throw new FormatException("id должен быть представлен целым числом");
        }
    }
}
