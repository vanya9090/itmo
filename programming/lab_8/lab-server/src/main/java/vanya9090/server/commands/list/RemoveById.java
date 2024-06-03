package vanya9090.server.commands.list;


import vanya9090.common.commands.CommandArgument;
import vanya9090.common.commands.CommandType;
import vanya9090.common.connection.Response;
import vanya9090.common.connection.Status;
import vanya9090.common.models.User;
import vanya9090.server.managers.CollectionManager;
import vanya9090.common.models.HumanBeing;
import vanya9090.common.commands.Command;
import vanya9090.common.exceptions.EmptyCollectionException;
import vanya9090.common.exceptions.FormatException;
import vanya9090.common.exceptions.NotFoundException;
import vanya9090.common.exceptions.WrongAmountOfElementsException;

import java.util.Map;

/**
 * команда для удаления человека по id
 *
 * @author vanya9090
 */
public class RemoveById extends Command {
    private final CollectionManager collectionManager;

    public RemoveById(CollectionManager collectionManager) {
        super("remove_by_id", "удалить элемент из коллекции по его id",
                new CommandArgument[]{new CommandArgument("id", Integer.class),
                                      new CommandArgument("user", User.class, CommandType.SYSTEM)});
        this.collectionManager = collectionManager;
    }

    @Override
    public Object[] apply(Map<String, Object> args) throws Exception{
        User user = (User) args.get("user");
        Integer id = (Integer) args.get("id");
        HumanBeing humanToDelete = collectionManager.getById(id);
//        try {
            if (humanToDelete == null) throw new NotFoundException("человек с таким id не найден");
            collectionManager.remove(humanToDelete, user);
//        } catch (Exception e) {
//            return new Response(e, Status.FORBIDDEN).getBody();
//        }
        return new String[]{};
    }
}
