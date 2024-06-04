package vanya9090.server.commands.list;

import vanya9090.common.commands.Command;
import vanya9090.common.commands.CommandArgument;
import vanya9090.common.commands.CommandType;
import vanya9090.common.models.User;
import vanya9090.server.Server;
import vanya9090.server.managers.CollectionManager;

import java.util.Map;

public class GetCollectionUsers extends Command {
    private final CollectionManager collectionManager;
    public GetCollectionUsers(CollectionManager collectionManager) {
        super("get_collection_users", "get_collection_users",
                new CommandArgument[]{new CommandArgument("user", User.class, CommandType.SYSTEM)},
                CommandType.SYSTEM);
        this.collectionManager = collectionManager;
    }

    @Override
    public Object[] apply(Map<String, Object> arg) throws Exception {
        System.out.println("get_collection_users command");
        System.out.println(collectionManager.getUserCollection().keySet());
        System.out.println(collectionManager.getUserCollection().values());
        return new Map[]{collectionManager.getUserCollection()};
    }
}
