package commands;

import exceptions.CollectionIsEmptyException;
import exceptions.NotFoundException;
import exceptions.WrongAmountOfElementsException;
import managers.CollectionManager;
import models.HumanBeing;
import models.forms.HumanBeingForm;
import utils.Logger;

public class RemoveById extends Command{
    private final CollectionManager collectionManager;
    private final Logger logger;

    public RemoveById(Logger logger, CollectionManager collectionManager){
        super("remove_by_id", "удалить элемент из коллекции по его id");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }
    @Override
    public void apply(String[] args) {
        try {
            if (args[1].isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.getSize() == 0) throw new CollectionIsEmptyException();

            var id = Integer.parseInt(args[1]);
            var humanToDelete = collectionManager.getById(id);
            if (humanToDelete == null) throw new NotFoundException();

            collectionManager.remove(humanToDelete);

            logger.info("Удалено успешно");

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
