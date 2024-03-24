package vanya9090.server.commands.list;

import vanya9090.server.commands.Command;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * вывод всех доступных команд
 *
 * @author vanya9090
 */
public class Help extends Command {
    private final Map<String, Command> commands;

    public Help(Map<String, Command> commands) {
        super("help", "вывести справку по доступным командам");
        this.commands = commands;
    }

    @Override
    public String apply(String[] args) {
        return this.commands.values().stream()
                .map(command -> String.format("%-36s%s%n", command.getName(), command.getDescription()))
                .collect(Collectors.joining());
    }
}
