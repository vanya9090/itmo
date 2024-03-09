package vanya9090.client;

import vanya9090.client.commands.Command;
import vanya9090.client.managers.CommandManager;
import vanya9090.common.util.Validators.Validator;

import java.util.Scanner;

public class Runner {
    private final Logger logger;
    private final CommandManager commandManager;
    private final Validator validator;
    public Runner(Logger logger, Validator validator, CommandManager commandManager) {
        this.logger = logger;
        this.validator = validator;
        this.commandManager = commandManager;
    }
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine().trim();
            String[] tokens = line.split(" ");
            validator.validateCommand(tokens);
            Command command = commandManager.getCommands().get(tokens[0]);
            command.apply(tokens);
    }
}