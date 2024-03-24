package vanya9090.common.connection;

public class Request extends Connection{
    String command;
    public Request(String commandName) {
        this.command = commandName;
    }

    public String getCommandName() {
        return this.command;
    }
}
