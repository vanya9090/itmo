package vanya9090.server.managers;


import vanya9090.common.commands.Command;

import java.util.HashMap;
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

    public static Map<String, Command> getCommands() {
        return commands;
    }
}
