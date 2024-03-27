package vanya9090.common.connection;

public class Request extends Connection {
    private final String commandName;
    private String[] argument;

    public Request(String commandName,  String[] argument) {
        this.commandName = commandName;
        this.argument = argument;
    }

    public String getCommandName() {
        return this.commandName;
    }
    public String[] getArgument() {
        return this.argument;
    }
    public void setArgument(String[] argument) {
        this.argument = argument;
    }
}