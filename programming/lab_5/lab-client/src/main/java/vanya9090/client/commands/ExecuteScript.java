package vanya9090.client.commands;

import vanya9090.common.exceptions.*;
import vanya9090.client.utils.ILogger;
import vanya9090.client.utils.Runner;

import java.io.FileNotFoundException;

public class ExecuteScript extends Command {
    private final Runner runner;
    private final ILogger logger;

    public ExecuteScript(Runner runner, ILogger logger) {
        super("execute_script", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        this.runner = runner;
        this.logger = logger;
    }

    @Override
    public void apply(String[] args) throws Exception {
        try {
            if (args[1].isEmpty()) throw new WrongAmountOfElementsException("пустой аргумент, введите название файла");
            this.runner.executeScript(args[1]);
        } catch (WrongAmountOfElementsException | ArrayIndexOutOfBoundsException | NotFoundException e) {
            throw new WrongAmountOfElementsException("пустой аргумент, введите название файла");
        }
    }
}
