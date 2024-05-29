package vanya9090.server.commands.list;


import vanya9090.common.commands.CommandArgument;
import vanya9090.common.commands.CommandType;
import vanya9090.common.models.User;
import vanya9090.server.managers.CollectionManager;
import vanya9090.common.commands.Command;
import vanya9090.common.exceptions.EmptyCollectionException;

import java.util.Map;

/**
 * удаляет все записи в коллекции
 *
 * @author vanya9090
 */
public class Clear extends Command {
    private final CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию", new CommandArgument[]{
                new CommandArgument("user", User.class, CommandType.SYSTEM)});
        this.collectionManager = collectionManager;
    }

    /**
     * @param args аргументы, переданные в командной строке
     * @return пустая строка
     * @throws EmptyCollectionException пустая ли коллекция
     */
    @Override
    public Object[] apply(Map<String, Object> args) throws Exception {
        if (collectionManager.getSize() == 0) throw new EmptyCollectionException();
        collectionManager.clear();
        return new String[]{};
    }
}
