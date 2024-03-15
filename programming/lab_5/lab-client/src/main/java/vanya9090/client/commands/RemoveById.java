package vanya9090.client.commands;


import vanya9090.common.exceptions.CollectionIsEmptyException;
import vanya9090.common.exceptions.NotFoundException;
import vanya9090.common.exceptions.WrongAmountOfElementsException;
import vanya9090.client.managers.CollectionManager;
import vanya9090.client.utils.ILogger;
import vanya9090.client.models.HumanBeing;

import java.util.Arrays;

public class RemoveById extends Command {
    private final CollectionManager collectionManager;
    private final ILogger logger;

    public RemoveById(ILogger logger, CollectionManager collectionManager) {
        super("remove_by_id", "удалить элемент из коллекции по его id");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }

    @Override
    public void apply(String[] args) {
        try {
            if (args[1].isEmpty()) throw new WrongAmountOfElementsException("пустой аргумент, введите id");
            if (collectionManager.getSize() == 0) throw new CollectionIsEmptyException("коллекция пуста");

            Integer id = Integer.parseInt(args[1]);
            HumanBeing humanToDelete = collectionManager.getById(id);
            if (humanToDelete == null) throw new NotFoundException("человек с таким id не найден");

            collectionManager.remove(humanToDelete);

            logger.info("Удалено успешно");

        } catch (CollectionIsEmptyException e) {
            logger.error("коллекция пуста");
        } catch (WrongAmountOfElementsException | ArrayIndexOutOfBoundsException e) {
            logger.error("пустой аргумент, введите id");
        } catch (NumberFormatException e) {
            logger.error("id должен быть представлен целым числом");
        } catch (NotFoundException e) {
            logger.error(e);
            logger.info("доступные id: " + Arrays.toString(collectionManager.getCollection().stream().map(HumanBeing::getId).toArray()));
        }

    }
}
