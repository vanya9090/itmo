package vanya9090.common.connection;

import vanya9090.common.commands.CommandArgument;

public class Request extends Connection {
    private final String commandName;
    private CommandArgument argument;

    public Request(String commandName,  CommandArgument argument) {
        this.commandName = commandName;
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