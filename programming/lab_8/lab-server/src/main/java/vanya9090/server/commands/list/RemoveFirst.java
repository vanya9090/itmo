package vanya9090.server.commands.list;


import vanya9090.common.commands.Command;
import vanya9090.common.commands.CommandArgument;
import vanya9090.common.commands.CommandType;
import vanya9090.common.models.User;
import vanya9090.server.managers.CollectionManager;
import vanya9090.common.exceptions.EmptyCollectionException;

import java.util.Map;

/**
 * удаление первого человека из коллекции
 *
 * @author vanya9090
 */
public class RemoveFirst extends Command {
    private final CollectionManager collectionManager;

    public RemoveFirst(CollectionManager collectionManager) {
        super("remove_first", "удалить первый элемент из коллекции",
                new CommandArgument[]{new CommandArgument("user", User.class, CommandType.SYSTEM)});
        this.collectionManager = collectionManager;
    }

    @Override
    public Object[] apply(Map<String, Object> args) throws Exception {
        User user = (User) args.get("user");
        if (this.collectionManager.getSize() == 0) throw new EmptyCollectionException();
        if (user.getLogin() != args.get("user")) {}
        this.collectionManager.removeFirst(user);
        return new String[]{};
    }
}
