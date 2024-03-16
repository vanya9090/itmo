package vanya9090.client.commands;


import vanya9090.client.managers.CollectionManager;
import vanya9090.common.exceptions.CollectionIsEmptyException;

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
     * @throws CollectionIsEmptyException пустая ли коллекция
     */
    @Override
    public String apply(String[] args) throws CollectionIsEmptyException {
        if (collectionManager.getSize() == 0) throw new CollectionIsEmptyException("коллекция пуста");
        collectionManager.clear();
        return "";
    }
}
