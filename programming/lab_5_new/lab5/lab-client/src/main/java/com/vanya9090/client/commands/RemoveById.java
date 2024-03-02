package com.vanya9090.client.commands;

import com.vanya9090.client.exceptions.CollectionIsEmptyException;
import com.vanya9090.client.exceptions.NotFoundException;
import com.vanya9090.client.exceptions.WrongAmountOfElementsException;
import com.vanya9090.client.managers.CollectionManager;
import com.vanya9090.client.models.HumanBeing;
import com.vanya9090.client.utils.Logger;

import java.util.Arrays;

public class RemoveById extends Command {
    private final CollectionManager collectionManager;
    private final Logger logger;

    public RemoveById(Logger logger, CollectionManager collectionManager) {
        super("remove_by_id", "удалить элемент из коллекции по его id");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }

    @Override
    public void apply(String[] args) {
        try {
            if (args[1].isEmpty()) {
                throw new WrongAmountOfElementsException();
            }
            if (collectionManager.getSize() == 0) {
                throw new CollectionIsEmptyException();
            }

            var id = Integer.parseInt(args[1]);
            var humanToDelete = collectionManager.getById(id);
            if (humanToDelete == null) {
                throw new NotFoundException();
            }

            collectionManager.remove(humanToDelete);

            logger.info("Удалено успешно");

        } catch (CollectionIsEmptyException e) {
            logger.error("коллекция пуста");
        } catch (WrongAmountOfElementsException | ArrayIndexOutOfBoundsException e) {
            logger.error("пустой аргумент, введите id");
        } catch (NumberFormatException e) {
            logger.error("id должен быть представлен целым числом");
        } catch (NotFoundException e) {
            logger.error("нет записи с таким id");
            logger.info("доступные id: " + Arrays.toString(collectionManager.getCollection().stream().map(HumanBeing::getId).toArray()));
        }

    }
}
