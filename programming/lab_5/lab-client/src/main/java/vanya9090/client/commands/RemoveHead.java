package vanya9090.client.commands;


import vanya9090.client.exceptions.EmptyCollectionException;
import vanya9090.client.managers.CollectionManager;
import vanya9090.client.utils.ILogger;

public class RemoveHead extends Command {
    private final ILogger logger;
    private final CollectionManager collectionManager;

    public RemoveHead(ILogger logger, CollectionManager collectionManager) {
        super("remove_head", "вывести первый элемент коллекции и удалить его");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }

    @Override
    public void apply(String[] args) {
        try {
            if (this.collectionManager.getSize() == 0) throw new EmptyCollectionException();
            logger.info("первый элемент удален:");
            logger.info(collectionManager.removeHead());
        } catch (EmptyCollectionException e) {
            logger.error(e);
        }
    }
}
