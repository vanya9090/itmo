package vanya9090.server.commands.list;

import vanya9090.common.commands.Command;
import vanya9090.common.commands.CommandArgument;
import vanya9090.common.commands.CommandType;
import vanya9090.common.models.User;
import vanya9090.server.Server;
import vanya9090.server.managers.CollectionManager;

import java.util.Map;

public class GetCollection extends Command {
    private final CollectionManager collectionManager;
    public GetCollection(CollectionManager collectionManager) {
        super("get_collection", "get_collection",
                new CommandArgument[]{new CommandArgument("user", User.class, CommandType.SYSTEM)},
                CommandType.SYSTEM);
        this.collectionManager = collectionManager;
    }

    @Override
    public Object[] apply(Map<String, Object> arg) throws Exception {
        return collectionManager.getCollection().toArray();
    }
}
