package vanya9090.server.commands.list;


import vanya9090.client.managers.CollectionManager;
import vanya9090.common.exceptions.EmptyCollectionException;
import vanya9090.server.commands.Command;

/**
 * удаляет все записи в коллекции
 *
 * @author vanya9090
 */
public class Clear extends Command {
    private final CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
    }

    /**
     * @param args аргументы, переданные в командной строке
     * @return пустая строка
     * @throws EmptyCollectionException пустая ли коллекция
     */
    @Override
    public String apply(String[] args) throws EmptyCollectionException {
        if (collectionManager.getSize() == 0) throw new EmptyCollectionException();
        collectionManager.clear();
        return "";
    }
}
