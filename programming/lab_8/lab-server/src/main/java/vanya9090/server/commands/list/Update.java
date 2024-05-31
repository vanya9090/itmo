package vanya9090.server.commands.list;

import vanya9090.common.commands.Command;
import vanya9090.common.commands.CommandArgument;
import vanya9090.common.commands.CommandType;
import vanya9090.common.commands.Formable;
import vanya9090.common.exceptions.NotFoundException;
import vanya9090.common.models.HumanBeing;
import vanya9090.common.models.User;
import vanya9090.server.managers.CollectionManager;

import java.util.Map;

public class Update extends Command implements Formable {
    private final CollectionManager collectionManager;
    public Update(CollectionManager collectionManager) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному",
                new CommandArgument[]{new CommandArgument("id", Integer.class),
                                      new CommandArgument("human", HumanBeing.class),
                                      new CommandArgument("user", User.class, CommandType.SYSTEM)});
        this.collectionManager = collectionManager;
    }

    @Override
    public Object[] apply(Map<String, Object> arg) throws Exception {
        User user = (User) arg.get("user");
        HumanBeing humanBeing = (HumanBeing) arg.get("human");
        int id = (int) arg.get("id");
        HumanBeing humanToUpdate = collectionManager.getById(id);
        if (humanToUpdate == null) throw new NotFoundException("человек с таким id не найден");
        collectionManager.update(humanBeing, id, user);
        return new String[]{};

    }
}