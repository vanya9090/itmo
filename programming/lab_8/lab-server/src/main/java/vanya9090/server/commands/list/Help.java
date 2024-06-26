package vanya9090.server.commands.list;

import vanya9090.common.commands.Command;
import vanya9090.common.commands.CommandArgument;
import vanya9090.common.commands.CommandType;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * вывод всех доступных команд
 * @author vanya9090
 */
public class Help extends Command {
    private final Map<String, Command> commands;

    public Help(Map<String, Command> commands) {
        super("help", "вывести справку по доступным командам", new CommandArgument[]{});
        this.commands = commands;
    }

    @Override
    public Object[] apply(Map<String, Object> args) {
//        return new Map[]{this.commands.values().
//                stream().
//                collect(Collectors.toMap(Command::getName, Command::getDescription))};
//        throw new RuntimeException();
        return new Object[]{this.commands.values().stream()
                .filter(command -> command.getCommandType() == CommandType.CLIENT)
                .map(command -> String.format("%s-%s%n", command.getName(), command.getDescription()))
                .collect(Collectors.joining())};
    }
}
