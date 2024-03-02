package com.vanya9090.client.utils;

import com.vanya9090.client.commands.Command;
import com.vanya9090.client.exceptions.RecursiveScriptException;
import com.vanya9090.client.managers.CommandManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Runner {
    private final ILogger logger;
    private final CommandManager commandManager;

    public Runner(ILogger logger, CommandManager commandManager) {
        this.logger = logger;
        this.commandManager = commandManager;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine().trim();
            String[] tokens = line.split(" ");
            var command = commandManager.getCommands().get(tokens[0]);
            if (command == null) {
                this.logger.warning("команда " + tokens[0] + " не найдена, наберите help для справки");
            } else {
                command.apply(tokens);
            }
        }
    }

    public void executeScript(String name) {
        try (Scanner fileReader = new Scanner(new File(name))) {
            if (!fileReader.hasNext()) {
                throw new NoSuchElementException();
            }
            while (fileReader.hasNext()) {
                String line = fileReader.nextLine().trim();
                String[] tokens = line.split(" ");
                Command command = commandManager.getCommands().get(tokens[0]);
                if (command.getName().equals("execute_script")) {
                    throw new RecursiveScriptException();
                }
                command.apply(tokens);
            }
        } catch (FileNotFoundException e) {
            logger.error("нет файла с названием: " + name);
        } catch (NoSuchElementException e) {
            logger.error("файл пустой");
        } catch (RecursiveScriptException e) {
            logger.error("скрипт рекурсивно себя запускает");
        }
    }
}
