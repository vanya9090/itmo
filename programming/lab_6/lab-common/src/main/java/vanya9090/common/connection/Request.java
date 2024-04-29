package vanya9090.common.connection;

import vanya9090.common.commands.Command;
import vanya9090.common.commands.CommandArgument;

import java.util.Map;

public class Request extends Connection {
    private String commandName;
    private Command command;
    private CommandArgument argument;

    public Request(String commandName,  Map<String, Object> arguments) {
        this.commandName = commandName;
        this.argument = argument;
    }

    public Request(Command command, Map<String, Object> arguments) {
        this.command = command;
        this.argument = argument;
    }

    public String getCommandName() {
        return this.commandName;
    }
    public CommandArgument getArgument() {
        return this.argument;
    }
    public void setArgument(CommandArgument argument) {
        this.argument = argument;
    }
}