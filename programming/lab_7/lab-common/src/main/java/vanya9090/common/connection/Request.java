package vanya9090.common.connection;

import vanya9090.common.commands.Command;
import vanya9090.common.models.User;

import java.util.Map;

public class Request extends Connection {
    private String commandName;
    private Command command;
    private Map<String, Object> argument;
    private User user;

    public Request(String commandName, Map<String, Object> arguments, User user) {
        this.commandName = commandName;
        this.argument = arguments;
        this.user = user;
    }

    public Request(String commandName, Map<String, Object> arguments) {
        this.commandName = commandName;
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
    public User getUser() {return this.user;}
}