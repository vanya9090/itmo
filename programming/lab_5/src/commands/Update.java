package commands;

import exceptions.CollectionIsEmptyException;
import exceptions.NotFoundException;
import exceptions.WrongAmountOfElementsException;
import managers.CollectionManager;
import models.HumanBeing;
import models.forms.HumanBeingForm;
import utils.Logger;

import java.util.Scanner;

public class Update extends Command{
    private final CollectionManager collectionManager;
    private final Logger logger;

    public Update(Logger logger, CollectionManager collectionManager) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }

    public void apply(String[] args) {
        try {
            if (args[1].isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.getSize() == 0) throw new CollectionIsEmptyException();

            var id = Integer.parseInt(args[1]);
            var humanToUpdate = collectionManager.getById(id);
            if (humanToUpdate == null) throw new NotFoundException();

            HumanBeingForm humanBeingForm = new HumanBeingForm(this.logger);
            HumanBeing humanBeing = humanBeingForm.create();

            humanToUpdate.update(humanBeing);
            logger.info("Обновлено успешно");

        } catch (CollectionIsEmptyException e) {
            logger.error("CollectionIsEmptyException");
        } catch (WrongAmountOfElementsException e) {
            logger.error("WrongAmountOfElementsException");
        } catch (NumberFormatException e) {
            logger.error("NumberFormatException");
        } catch (NotFoundException e) {
            logger.error("NotFoundException");
        }

    }
}
