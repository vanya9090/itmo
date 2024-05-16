package vanya9090.server;

import vanya9090.server.commands.list.*;
import vanya9090.server.connection.ConnectionManager;
import vanya9090.server.connection.UDPConnection;
import vanya9090.server.commands.CommandExecutor;
import vanya9090.common.util.ILogger;
import vanya9090.common.util.Logger;
import vanya9090.server.managers.CollectionManager;
import vanya9090.common.commands.CommandManager;
import vanya9090.server.managers.DataBaseManager;
import vanya9090.server.managers.UserManager;

public final class Server {
    public static ILogger logger = new Logger();
    public static UserManager userManager;
    private final static String ENV_KEY = "lab5";
    public static void main(String[] args) throws Exception {
        CommandManager commandManager = new CommandManager();
        DataBaseManager dataBaseManager = new DataBaseManager();
//        dataBaseManager.deleteAllCollection();
        CollectionManager collectionManager = new CollectionManager(dataBaseManager);
        collectionManager.setCollection(dataBaseManager.read());
        userManager = new UserManager();


        commandManager.register("help", new Help(CommandManager.getCommands()));
        commandManager.register("login", new Auth(userManager));
        commandManager.register("signin", new Register(userManager));
        commandManager.register("get_commands", new GetCommands(CommandManager.getCommands()));
        commandManager.register("info", new Info(collectionManager));
        commandManager.register("show", new Show(collectionManager));
        commandManager.register("add", new Add(collectionManager));
        commandManager.register("update", new Update(collectionManager));
        commandManager.register("remove_by_id", new RemoveById(collectionManager));
        commandManager.register("clear", new Clear(collectionManager));
        commandManager.register("save", new Save(collectionManager, ENV_KEY));
        commandManager.register("execute_script", new ExecuteScript());
        commandManager.register("exit", new Exit());
        commandManager.register("remove_first", new RemoveFirst(collectionManager));
        commandManager.register("remove_head", new RemoveHead(collectionManager));
        commandManager.register("add_if_min", new AddIfMin(collectionManager));
        commandManager.register("sum_of_impact_speed", new SumOfImpactSpeed(collectionManager));
        commandManager.register("filter_by_weapon_type", new FilterByWeaponType(collectionManager));
        commandManager.register("print_field_descending_impact_speed", new PrintFieldDescendingImpactSpeed(collectionManager));


        ConnectionManager udpManager = new UDPConnection();
        udpManager.setRequestCallback(CommandExecutor::execute);
        udpManager.run();
    }
}
