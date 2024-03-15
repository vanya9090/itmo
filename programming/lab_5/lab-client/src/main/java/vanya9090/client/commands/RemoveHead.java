package vanya9090.client.commands;


import vanya9090.common.exceptions.EmptyCollectionException;
import vanya9090.client.managers.CollectionManager;
import vanya9090.client.utils.ILogger;
import vanya9090.common.exceptions.EmptyFieldException;

public class RemoveHead extends Command {
    private final CollectionManager collectionManager;

    public RemoveHead(CollectionManager collectionManager) {
        super("remove_head", "вывести первый элемент коллекции и удалить его");
        this.collectionManager = collectionManager;
    }

    @Override
    public String apply(String[] args) throws EmptyFieldException, EmptyCollectionException {
        if (this.collectionManager.getSize() == 0) throw new EmptyCollectionException();
        return collectionManager.removeHead().toString() + "\n";
    }
}
