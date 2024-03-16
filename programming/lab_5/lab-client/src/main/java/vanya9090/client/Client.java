package vanya9090.client;

import com.google.gson.JsonSyntaxException;
import vanya9090.client.commands.*;
import vanya9090.client.managers.CollectionManager;
import vanya9090.client.managers.CommandManager;
import vanya9090.client.managers.JSONManager;
import vanya9090.client.utils.ILogger;
import vanya9090.client.utils.Logger;
import vanya9090.client.utils.Runner;
import vanya9090.common.exceptions.*;

import java.io.FileNotFoundException;

public final class Client {
    private final static String ENV_KEY = "lab5";

    public static void main(String[] args) {
        ILogger logger = new Logger();
        CommandManager commandManager = new CommandManager();
        Runner runner = new Runner(logger, commandManager);
        JSONManager jsonManager = new JSONManager();
        CollectionManager collectionManager = new CollectionManager(jsonManager);

        try {
            collectionManager.readCollection(ENV_KEY);
            logger.info("коллекция успешно загружена");
        } catch (ValidateException | JsonSyntaxException | EmptyFileException | NotFoundException | AccessException |
                 FormatException | FileNotFoundException e) {
            logger.error(e);
        }

        commandManager.register("help", new Help(commandManager.getCommands()));
        commandManager.register("info", new Info(collectionManager));
        commandManager.register("show", new Show(collectionManager));
        commandManager.register("add", new Add(logger, collectionManager));
        commandManager.register("update", new Update(logger, collectionManager));
        commandManager.register("remove_by_id", new RemoveById(collectionManager));
        commandManager.register("clear", new Clear(collectionManager));
        commandManager.register("save", new Save(collectionManager, ENV_KEY));
        commandManager.register("execute_script", new ExecuteScript(runner));
        commandManager.register("exit", new Exit());
        commandManager.register("remove_first", new RemoveFirst(collectionManager));
        commandManager.register("remove_head", new RemoveHead(collectionManager));
        commandManager.register("add_if_min", new AddIfMin(logger, collectionManager));
        commandManager.register("sum_of_impact_speed", new SumOfImpactSpeed(collectionManager));
        commandManager.register("filter_by_weapon_type", new FilterByWeaponType(collectionManager));
        commandManager.register("print_field_descending_impact_speed", new PrintFieldDescendingImpactSpeed(collectionManager));
        runner.run();
    }
}
