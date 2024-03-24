package vanya9090.client.utils;


import vanya9090.client.commands.Command;
import vanya9090.client.commands.Executable;
import vanya9090.client.managers.CommandManager;
import vanya9090.common.exceptions.AccessException;
import vanya9090.common.exceptions.NotFoundException;
import vanya9090.common.exceptions.RecursiveScriptException;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * класс для запуска команд
 *
 * @author vanya9090
 */
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
            Command command = commandManager.getCommands().get(tokens[0]);
            if (command == null) {
                this.logger.warning("команда " + tokens[0] + " не найдена, наберите help для справки");
            } else {
                try {
                    logger.field(command.apply(tokens));
                    logger.success("команда " + command.getName() + " успешно выполнена");
                } catch (Exception e) {
                    logger.error(e);
                    logger.error("команда не выполнена");
                }
            }
        }
    }

    public void executeScript(String name) throws Exception {
        if (!new File(name).exists()) throw new FileNotFoundException();
        if (!Files.isReadable(Paths.get(name))) throw new AccessException("нет прав доступа для записи в файл");
        Scanner fileReader = new Scanner(new File(name));
        if (!fileReader.hasNext()) throw new NotFoundException("файл пустой");

        while (fileReader.hasNext()) {
            String line = fileReader.nextLine().trim();
            String[] tokens = line.split(" ");
            Command command = commandManager.getCommands().get(tokens[0]);
            if (command == null) throw new NotFoundException("команды не существует");
            if (command.getName().equals("execute_script"))
                throw new RecursiveScriptException("скрипт рекурсивно себя запускает");

            if (command.getName().equals("add") || command.getName().equals("add_if_min") || command.getName().equals("update")) {
                Executable executable = (Executable) command;
                executable.apply(tokens, fileReader);
            } else {
                command.apply(tokens);
            }
        }
    }
}