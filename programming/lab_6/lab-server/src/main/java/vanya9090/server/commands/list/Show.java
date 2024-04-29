package vanya9090.server.commands.list;


import vanya9090.common.commands.CommandArgument;
import vanya9090.server.managers.CollectionManager;
import vanya9090.common.models.HumanBeing;
import vanya9090.common.commands.Command;
import vanya9090.common.exceptions.EmptyCollectionException;

import java.util.stream.Collectors;

/**
 * вывод всех элементов в коллекции
 *
 * @author vanya9090
 */
public class Show extends Command {
    private final CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении",
                new CommandArgument[]{});
        this.collectionManager = collectionManager;
    }

    @Override
    public Object[] apply(CommandArgument args) throws EmptyCollectionException {
        if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
        return collectionManager.getCollection().stream()
                .map(HumanBeing::toString).toArray();
    }
}
