package vanya9090.server.commands.list;

import vanya9090.common.commands.Command;
import vanya9090.common.commands.CommandArgument;
import vanya9090.common.commands.CommandType;
import vanya9090.common.models.HumanBeing;
import vanya9090.common.commands.Formable;
import vanya9090.common.models.User;
import vanya9090.server.managers.CollectionManager;

import java.util.Map;

public class Add extends Command implements Formable {
    private final CollectionManager collectionManager;
    public Add(CollectionManager collectionManager) {
        super("add", "добавить новый элемент в коллекцию",
                new CommandArgument[]{new CommandArgument("human", HumanBeing.class),
                new CommandArgument("user", User.class, CommandType.SYSTEM)});

        this.collectionManager = collectionManager;
    }

    @Override
    public Object[] apply(Map<String, Object> arg) throws Exception {
        User user = (User) arg.get("user");
        HumanBeing humanBeing = (HumanBeing) arg.get("human");
        humanBeing.setId(collectionManager.getNextId());
        collectionManager.add(humanBeing, user);
        return new String[]{};
    }
}
