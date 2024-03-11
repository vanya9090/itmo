package com.vanya9090.client;

import com.vanya9090.client.commands.RemoveFirst;
import com.vanya9090.client.commands.RemoveHead;
import com.vanya9090.client.commands.Save;
import com.vanya9090.client.commands.Show;
import com.vanya9090.client.commands.FilterByWeaponType;
import com.vanya9090.client.commands.RemoveById;
import com.vanya9090.client.commands.SumOfImpactSpeed;
import com.vanya9090.client.commands.Update;
import com.vanya9090.client.commands.Add;
import com.vanya9090.client.commands.AddIfMin;
import com.vanya9090.client.commands.Clear;
import com.vanya9090.client.commands.ExecuteScript;
import com.vanya9090.client.commands.Exit;
import com.vanya9090.client.commands.Help;
import com.vanya9090.client.commands.Info;
import com.vanya9090.client.commands.PrintFieldDescendingImpactSpeed;
import com.vanya9090.client.managers.CollectionManager;

import com.vanya9090.client.exceptions.*;
import com.vanya9090.client.managers.CommandManager;
import com.vanya9090.client.managers.JSONManager;
import com.vanya9090.client.utils.Logger;
import com.vanya9090.client.utils.Runner;

public final class Client {
    private final static String ENV_KEY = "lab5";

    public static void main(String[] args) {
        Logger logger = new Logger();
        CommandManager commandManager = new CommandManager();
        Runner runner = new Runner(logger, commandManager);
        JSONManager jsonManager = new JSONManager(logger);
        CollectionManager collectionManager = new CollectionManager(jsonManager);


        try {
            collectionManager.readCollection(jsonManager.readFile(ENV_KEY));
        } catch (WrongFieldsException | EmptyFieldException | WrongPathException | ReadException | ParseException | NullFieldException e) {
            logger.error(e);
        }

        commandManager.register("help", new Help(logger, commandManager.getCommands()));
        commandManager.register("info", new Info(logger, collectionManager));
        commandManager.register("show", new Show(logger, collectionManager));
        commandManager.register("add", new Add(logger, collectionManager));
        commandManager.register("update", new Update(logger, collectionManager));
        commandManager.register("remove_by_id", new RemoveById(logger, collectionManager));
        commandManager.register("clear", new Clear(logger, collectionManager));
        commandManager.register("save", new Save(collectionManager, jsonManager, ENV_KEY));
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
