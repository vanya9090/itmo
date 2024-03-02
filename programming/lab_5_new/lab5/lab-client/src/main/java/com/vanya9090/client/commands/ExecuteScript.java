package com.vanya9090.client.commands;

import com.vanya9090.client.exceptions.WrongAmountOfElementsException;
import com.vanya9090.client.utils.Logger;
import com.vanya9090.client.utils.Runner;

public class ExecuteScript extends Command {
    private final Runner runner;
    private final Logger logger;

    public ExecuteScript(Runner runner, Logger logger) {
        super("execute_script", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        this.runner = runner;
        this.logger = logger;
    }

    @Override
    public void apply(String[] args) {
        try {
            if (args[1].isEmpty()) {
                throw new WrongAmountOfElementsException();
            }
            this.runner.executeScript(args[1]);
        } catch (WrongAmountOfElementsException | ArrayIndexOutOfBoundsException e) {
            logger.error("пустой аргумент, введите название файла");
        }
    }
}
