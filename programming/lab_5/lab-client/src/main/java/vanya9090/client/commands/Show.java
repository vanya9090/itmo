package vanya9090.client.commands;


import vanya9090.common.exceptions.EmptyCollectionException;
import vanya9090.client.managers.CollectionManager;
import vanya9090.client.models.HumanBeing;
import vanya9090.client.utils.ILogger;

public class Show extends Command {
    private final CollectionManager collectionManager;
    private final ILogger logger;

    public Show(ILogger logger, CollectionManager collectionManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
        this.logger = logger;
    }

    @Override
    public void apply(String[] args) {
        try {
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
            for (HumanBeing human : collectionManager.getCollection()) {
                logger.info(human);
            }
        } catch (EmptyCollectionException e) {
            logger.error(e);
        }
    }
}
