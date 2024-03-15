package vanya9090.client.commands;


import vanya9090.common.exceptions.CollectionIsEmptyException;
import vanya9090.client.managers.CollectionManager;
import vanya9090.client.utils.ILogger;

public class Clear extends Command {
    private final CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public String apply(String[] args) throws CollectionIsEmptyException {
        if (collectionManager.getSize() == 0) throw new CollectionIsEmptyException("коллекция пуста");
        collectionManager.clear();
        return "";
    }
}
