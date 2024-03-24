package com.vanya9090.client.commands;

import com.vanya9090.client.exceptions.*;
import com.vanya9090.client.managers.CollectionManager;
import com.vanya9090.client.models.HumanBeing;
import com.vanya9090.client.models.forms.HumanBeingForm;
import com.vanya9090.client.utils.ILogger;

import java.util.Arrays;
import java.util.Scanner;

public class UpdateExecute extends Command{
    private final CollectionManager collectionManager;
    private final ILogger logger;

    public UpdateExecute(ILogger logger, CollectionManager collectionManager) {
        super("update_execute", "обновить значение элемента коллекции, id которого равен заданному");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }

    public void apply(String[] args, Scanner fileReader) {
        try {
            if (args[1].isEmpty()) {
                throw new WrongAmountOfElementsException();
            }
            if (collectionManager.getSize() == 0) {
                throw new CollectionIsEmptyException();
            }

            var id = Integer.parseInt(args[1].trim());
            var humanToUpdate = collectionManager.getById(id);
            if (humanToUpdate == null) {
                throw new NotFoundException();
            }

            HumanBeingForm humanBeingForm = new HumanBeingForm(this.logger, fileReader, true);
            HumanBeing humanBeing = humanBeingForm.create();

            humanToUpdate.update(humanBeing);
            logger.success("Обновлено успешно");

        } catch (CollectionIsEmptyException e) {
            logger.error("коллекция пуста");
        } catch (WrongAmountOfElementsException | ArrayIndexOutOfBoundsException e) {
            logger.error("пустой аргумент, введите id");
        } catch (NumberFormatException e) {
            logger.error("id должен быть представлен целым числом");
        } catch (NotFoundException e) {
            logger.error("нет записи с таким id");
            logger.info("доступные id: " + Arrays.toString(collectionManager.getCollection().stream().map(HumanBeing::getId).toArray()));
        } catch (ParseException | EmptyFieldException | WrongFieldsException e) {
            logger.error(e);
        }
    }

    @Override
    public void apply(String[] args) {

    }
}