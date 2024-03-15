package vanya9090.client;

import com.google.gson.JsonSyntaxException;
import vanya9090.client.commands.*;
import vanya9090.client.managers.CollectionManager;
import vanya9090.client.managers.CommandManager;
import vanya9090.client.managers.JSONManager;
import vanya9090.client.utils.ExecuteLogger;
import vanya9090.client.utils.ILogger;
import vanya9090.client.utils.Logger;
import vanya9090.client.utils.Runner;
import vanya9090.common.exceptions.AccessException;
import vanya9090.common.exceptions.EmptyFileException;
import vanya9090.common.exceptions.NotFoundException;
import vanya9090.common.exceptions.ValidateException;

public final class Client {
    private final static String ENV_KEY = "lab5";

    public static void main(String[] args)  {
        ILogger logger = new Logger();
        ILogger executeLoger = new ExecuteLogger();
        CommandManager commandManager = new CommandManager();
        Runner runner = new Runner(logger, commandManager);
        JSONManager jsonManager = new JSONManager(logger);
        CollectionManager collectionManager = new CollectionManager(jsonManager);

        try {
            collectionManager.readCollection(ENV_KEY);
            logger.info("коллекция успешно загружена");
        } catch (ValidateException | JsonSyntaxException | EmptyFileException | NotFoundException | AccessException e) {
            logger.error(e);
        }

        commandManager.register("help", new Help(logger, commandManager.getCommands())); //done
        commandManager.register("info", new Info(logger, collectionManager)); //done
        commandManager.register("show", new Show(logger, collectionManager)); //done
        commandManager.register("add", new Add(logger, collectionManager)); //done
        commandManager.register("update", new Update(logger, collectionManager)); //done
        commandManager.register("remove_by_id", new RemoveById(logger, collectionManager)); //done
        commandManager.register("clear", new Clear(logger, collectionManager)); //done
        commandManager.register("save", new Save(collectionManager, jsonManager)); // done
        commandManager.register("execute_script", new ExecuteScript(runner, logger));
        commandManager.register("exit", new Exit(logger));
        commandManager.register("remove_first", new RemoveFirst(logger, collectionManager));
        commandManager.register("remove_head", new RemoveHead(logger, collectionManager));
        commandManager.register("add_if_min", new AddIfMin(logger, collectionManager));
        commandManager.register("sum_of_impact_speed", new SumOfImpactSpeed(logger, collectionManager));
        commandManager.register("filter_by_weapon_type", new FilterByWeaponType(logger, collectionManager));
        commandManager.register("print_field_descending_impact_speed", new PrintFieldDescendingImpactSpeed(logger, collectionManager));
        runner.run();
    }
}
