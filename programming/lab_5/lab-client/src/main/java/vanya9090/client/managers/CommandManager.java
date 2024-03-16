package vanya9090.client.managers;


import vanya9090.client.commands.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * командный менеджер
 *
 * @author vanya9090
 */
public class CommandManager {
    private static final Map<String, Command> commands = new HashMap<>();

    public void register(String commandName, Command command) {
        commands.put(commandName, command);
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
