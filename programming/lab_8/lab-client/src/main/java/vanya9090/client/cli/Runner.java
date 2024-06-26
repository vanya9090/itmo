package vanya9090.client.cli;

import vanya9090.client.App;
import vanya9090.client.connection.UDPClient;
import vanya9090.client.forms.HumanBeingForm;
import vanya9090.client.forms.LoginForm;
import vanya9090.common.commands.*;
import vanya9090.common.connection.Request;
import vanya9090.common.connection.Response;
import vanya9090.common.connection.Status;
import vanya9090.common.handlers.HandleManager;
import vanya9090.common.handlers.Handler;
import vanya9090.common.models.HumanBeing;
import vanya9090.common.models.User;
import vanya9090.common.util.FakeLogger;
import vanya9090.common.util.ILogger;
import vanya9090.common.exceptions.*;
import vanya9090.common.validators.Validator;
import vanya9090.common.validators.ValidatorManager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * класс для запуска команд
 *
 * @author vanya9090
 */
public class Runner {
    private final ILogger logger;
    private final UDPClient client;
    private final Map<String, CommandArgument[]> commands;

    public Runner(UDPClient client, Map<String, CommandArgument[]> commands) {
        this.logger = App.logger;
        this.client = client;
        this.commands = commands;
    }
    public Map<String, Object> getArgsMap(String commandName, String[] arg, Scanner scanner, ILogger logger, boolean isExecute) throws Exception {
        Map<String, Handler<?>> handlers = new HandleManager().getHandlers();
        Map<String, Validator<?>> validators = new ValidatorManager().getValidators();

        Map<String, Object> argsMap = new HashMap<>();
        Object field;
        CommandArgument[] neededArgs = this.commands.get(commandName);
        for (CommandArgument commandArgument : neededArgs) {
            if (commandArgument.getType() == User.class && commandArgument.getCommandType() == CommandType.SYSTEM) {
                if (App.user == null) {
                    throw new AccessException("Пользователь не вошел в аккаунт");
                }
                field = App.user;
            } else if (commandArgument.getType() == HumanBeing.class){
                field = new HumanBeingForm(logger, scanner, isExecute).create();
            } else if(commandArgument.getType() == User.class){
                field = new LoginForm(logger, scanner, isExecute).create();
            } else {
                try {
                    field = handlers.get(commandArgument.getType().getSimpleName()).handle(arg[1], commandArgument.getName());
                } catch (ArrayIndexOutOfBoundsException e){
                    throw new EmptyFieldException(commandArgument.getName());
                }
                Validator<Object> validator = (Validator<Object>) validators.get(commandArgument.getName());
                if (!validator.validate(field)) System.out.println("валидация не прошла!!!!!!!");
            }
            argsMap.put(commandArgument.getName(), field);
        }
        return argsMap;
    }

    public void send(String commandName, String[] tokens, Scanner scanner, ILogger logger, boolean isExecute){
        Response response;
        Map<String, Object> argsMap;
        try {
            argsMap = this.getArgsMap(commandName, tokens, scanner, logger, isExecute);
            response = client.request(new Request(commandName, argsMap, App.user));
            if (response.getCode() == Status.CREATED) {
                App.user = (User) response.getBody()[0];
                this.logger.success("команда " + commandName + " успешно выполнена");
            }
            if (response.getCode() == Status.OK) {
                for (Object object : response.getBody()) {
                    this.logger.info(object);
                }
                this.logger.success("команда " + commandName + " успешно выполнена");
            } else {
                this.logger.error(response.getMessage());
            }
        } catch (Exception e){
            this.logger.warning(e);
        }
    }

    public void run(InputStream inputStream, ILogger logger, boolean isExecute) {
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()) {
            String line = scanner.nextLine().trim();
            String[] tokens = line.split(" ");
            String commandName = tokens[0];

            if (!this.commands.containsKey(commandName)) {
                this.logger.warning("команда " + commandName + " не найдена, наберите help для справки");
                continue;
            }
            if (Objects.equals(commandName, "exit")){
                this.send("exit", tokens, scanner, logger, isExecute);
                System.exit(0);
            }
            if (Objects.equals(commandName, "execute_script")){
                try {
                    if (!new File(tokens[1]).exists()) throw new NotFoundException("файла не существует");
                    if (!Files.isReadable(Paths.get(tokens[1]))) throw new AccessException("нет прав доступа для записи в файл");
                    this.run(Files.newInputStream(new File(tokens[1]).toPath()), new FakeLogger(), true);
                }catch (Exception e){
                    this.logger.warning(e);
                    continue;
                }
            }
            this.send(commandName, tokens, scanner, logger, isExecute);
        }
    }
}
