package vanya9090.server.commands.list;


import vanya9090.common.commands.Command;
import vanya9090.server.managers.CollectionManager;
import vanya9090.common.exceptions.EmptyCollectionException;

/**
 * удаление первого человека из коллекции
 *
 * @author vanya9090
 */
public class RemoveFirst extends Command {
    private final CollectionManager collectionManager;

    public RemoveFirst(CollectionManager collectionManager) {
        super("remove_first", "удалить первый элемент из коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public String apply(String[] args) throws EmptyCollectionException {
        if (this.collectionManager.getSize() == 0) throw new EmptyCollectionException();
        this.collectionManager.removeFirst();
        return "done";
    }
}
