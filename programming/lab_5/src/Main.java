import commands.*;
import managers.CollectionManager;
import managers.CommandManager;
import managers.JSONManager;
import utils.Logger;
import utils.Runner;

public class Main {
    public static void main(String[] args) {
        Logger logger = new Logger();
        CommandManager commandManager = new CommandManager();
        Runner runner = new Runner(logger, commandManager);
        JSONManager jsonManager = new JSONManager(logger);
        CollectionManager collectionManager = new CollectionManager(jsonManager);
        collectionManager.readCollection("src/file.json");
        commandManager.register("help", new Help(logger, commandManager.getCommands()));
        commandManager.register("info", new Info(logger, collectionManager));
        commandManager.register("show", new Show(logger, collectionManager));
        commandManager.register("add", new Add(logger, collectionManager));
        commandManager.register("update", new Update(logger, collectionManager));
        commandManager.register("remove_by_id", new RemoveById(logger, collectionManager));
        commandManager.register("clear", new Clear(logger, collectionManager));
        commandManager.register("save", new Save(logger, collectionManager, jsonManager));
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