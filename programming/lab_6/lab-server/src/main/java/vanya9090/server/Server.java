package vanya9090.server;

import vanya9090.common.exceptions.*;
import vanya9090.server.commands.list.*;
import vanya9090.server.connection.ConnectionManager;
import vanya9090.server.connection.UDPConnection;
import vanya9090.server.commands.CommandExecutor;
import vanya9090.common.util.ILogger;
import vanya9090.common.util.Logger;
import vanya9090.server.managers.CollectionManager;
import vanya9090.server.managers.CommandManager;
import vanya9090.server.managers.JSONManager;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Server {
    public static ILogger logger = new Logger();
    private final static String ENV_KEY = "lab5";
    public static void main(String[] args) throws IOException, AccessException, EmptyFileException, NotFoundException, FormatException, ReadException, WrongFieldsException, NullFieldException, WrongPathException, ParseException, EmptyFieldException {
        CommandManager commandManager = new CommandManager();
//        Runner runner = new Runner(logger, commandManager);
        JSONManager jsonManager = new JSONManager();
        CollectionManager collectionManager = new CollectionManager(jsonManager);
        collectionManager.readCollection(jsonManager.readFile(ENV_KEY));

        try {
            Map<Integer, List<Exception>> exceptionMap = collectionManager.readCollection(jsonManager.readFile(ENV_KEY));
            for(Map.Entry<Integer, List<Exception>> entry : exceptionMap.entrySet()) {
                Integer key = entry.getKey();
                List<Exception> value = entry.getValue();
                logger.error("запись с номером " + key + " имеет ошибки: " + value.stream().map(Exception::toString).collect(Collectors.joining(", ")));
            }
        } catch (Exception e) {
            logger.error(e);
        }


        commandManager.register("help", new Help(commandManager.getCommands()));
        commandManager.register("info", new Info(collectionManager));
        commandManager.register("show", new Show(collectionManager));
        commandManager.register("add", new AddNew(collectionManager));
//        commandManager.register("update", new Update(logger, collectionManager));
        commandManager.register("remove_by_id", new RemoveById(collectionManager));
        commandManager.register("clear", new Clear(collectionManager));
//        commandManager.register("save", new Save(collectionManager, ENV_KEY));
//        commandManager.register("execute_script", new ExecuteScript(runner));
        commandManager.register("exit", new Exit());
        commandManager.register("remove_first", new RemoveFirst(collectionManager));
        commandManager.register("remove_head", new RemoveHead(collectionManager));
//        commandManager.register("add_if_min", new AddIfMin(logger, collectionManager));
        commandManager.register("sum_of_impact_speed", new SumOfImpactSpeed(collectionManager));
        commandManager.register("filter_by_weapon_type", new FilterByWeaponType(collectionManager));
        commandManager.register("print_field_descending_impact_speed", new PrintFieldDescendingImpactSpeed(collectionManager));


        ConnectionManager udpManager = new UDPConnection();
        udpManager.setRequestCallback(CommandExecutor::execute);
        udpManager.run();
    }
}
