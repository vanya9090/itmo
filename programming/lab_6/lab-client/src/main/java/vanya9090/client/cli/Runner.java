package vanya9090.client.cli;

import vanya9090.client.Client;
import vanya9090.client.connection.UDPClient;
import vanya9090.client.forms.HumanBeingForm;
import vanya9090.common.commands.Command;
import vanya9090.common.commands.CommandArgument;
import vanya9090.common.connection.Request;
import vanya9090.common.connection.Response;
import vanya9090.common.models.HumanBeing;
import vanya9090.common.util.ILogger;
import vanya9090.common.exceptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * класс для запуска команд
 *
 * @author vanya9090
 */
public class Runner {
    private final ILogger logger;
    private final UDPClient client;
    private final Map<String, String> commands;

    public Runner(UDPClient client, Map<String, String> commands) {
        this.logger = Client.logger;
        this.client = client;
        this.commands = commands;
    }

    public void run() throws IOException, ClassNotFoundException, ParseException, EmptyFieldException {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine().trim();
            String[] tokens = line.split(" ");
            if (!this.commands.containsKey(tokens[0])) {
                this.logger.warning("команда " + tokens[0] + " не найдена, наберите help для справки");
            } else {
                Response response = null;
                if (Objects.equals(tokens[0], "add")) {
                    HumanBeing humanBeing = new HumanBeingForm(this.logger, new Scanner(System.in), false).create();
                    response = client.request(new Request(tokens[0], new CommandArgument().withModelArg(humanBeing)));
                } else {
                    response = client.request(new Request(tokens[0], new CommandArgument().withStringArg(tokens[0])));
                }
                logger.info(response.getBody());
            }
//            try {
//            } catch (Exception e) {
//                logger.error(e);
//            }
//            if (command == null) {
//                this.logger.warning("команда " + tokens[0] + " не найдена, наберите help для справки");
//            } else {
//                try {
//                    logger.field(command.apply(tokens));
//                    logger.success("команда " + command.getName() + " успешно выполнена");
//                } catch (Exception e) {
//                    logger.error(e);
//                    logger.error("команда не выполнена");
//                }
        }
    }

//    public void executeScript(String name) throws Exception {
//        if (!new File(name).exists()) throw new FileNotFoundException();
//        if (!Files.isReadable(Paths.get(name))) throw new AccessException("нет прав доступа для записи в файл");
//        Scanner fileReader = new Scanner(new File(name));
//        if (!fileReader.hasNext()) throw new NotFoundException("файл пустой");
//
//        while (fileReader.hasNext()) {
//            String line = fileReader.nextLine().trim();
//            String[] tokens = line.split(" ");
//            Command command = commandManager.getCommands().get(tokens[0]);
//            if (command == null) throw new NotFoundException("команды не существует");
//            if (command.getName().equals("execute_script"))
//                throw new RecursiveScriptException("скрипт рекурсивно себя запускает");
//
//            if (command.getName().equals("add") || command.getName().equals("add_if_min") || command.getName().equals("update")) {
//                Executable executable = (Executable) command;
//                executable.apply(tokens, fileReader);
//            } else {
//                command.apply(tokens);
//            }
//        }
//    }
}
