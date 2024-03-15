package vanya9090.client.commands;

import vanya9090.client.commands.Command;
import vanya9090.client.utils.ILogger;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
