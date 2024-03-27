package vanya9090.server.commands.list;

import vanya9090.server.managers.CollectionManager;
import vanya9090.common.commands.Command;
import vanya9090.common.exceptions.AccessException;
import vanya9090.common.exceptions.NotFoundException;

/**
 * сохранение коллекции в файл
 *
 * @author vanya9090
 */
public class Save extends Command {
    private final CollectionManager collectionManager;
    private final String envKey;

    public Save(CollectionManager collectionManager, String envKey) {
        super("save", "сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
        this.envKey = envKey;
    }

    @Override
    public String apply(String[] args) throws AccessException, NotFoundException {
        this.collectionManager.writeCollection(this.envKey);
        return "";
    }
}
