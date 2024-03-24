package vanya9090.server.commands.list;


import vanya9090.client.managers.CollectionManager;
import vanya9090.client.models.HumanBeing;
import vanya9090.common.exceptions.EmptyCollectionException;
import vanya9090.server.commands.Command;

import java.util.stream.Collectors;

/**
 * вывод всех элементов в коллекции
 *
 * @author vanya9090
 */
public class Show extends Command {
    private final CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
    }

    @Override
    public String apply(String[] args) throws EmptyCollectionException {
        if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
        return collectionManager.getCollection().stream()
                .map(HumanBeing::toString)
                .collect(Collectors.joining("\n")) + "\n";
    }
}
