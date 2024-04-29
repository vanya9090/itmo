package vanya9090.common.connection;

import vanya9090.common.commands.Command;
import vanya9090.common.commands.CommandArgument;

import java.util.Map;

public class Request extends Connection {
    private String commandName;
    private Command command;
    private Map<String, Object> argument;

    public Request(String commandName,  Map<String, Object> arguments) {
        this.commandName = commandName;
        this.argument = arguments;
    }

    public Request(Command command, Map<String, Object> arguments) {
        this.command = command;
        this.argument = arguments;
    }

    public String getCommandName() {
        return this.commandName;
    }
    public Map<String, Object> getArgument() {
        return this.argument;
    }
    public void setArgument(Map<String, Object> argument) {
        this.argument = argument;
    }
}