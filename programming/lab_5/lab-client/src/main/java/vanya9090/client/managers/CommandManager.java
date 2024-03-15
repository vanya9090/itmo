package vanya9090.client.managers;


import vanya9090.client.commands.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandManager {
    private static final Map<String, Command> commands = new HashMap<>();
    private final List<Command> history = new ArrayList<>();

    public void register(String commandName, Command command) {
        commands.put(commandName, command);
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    public List<Command> getHistory() {
        return history;
    }

    public void addToHistory(Command command) {
        history.add(command);
    }
}
