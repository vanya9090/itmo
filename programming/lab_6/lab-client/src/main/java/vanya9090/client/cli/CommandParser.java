package vanya9090.client.cli;

import vanya9090.common.commands.Command;
import vanya9090.common.commands.CommandArgument;
import vanya9090.common.commands.CommandManager;

public class CommandParser {
    String[] tokens;
    public CommandParser(String[] tokens) {
        this.tokens = tokens;
    }

    public Command getCommand() {
        return CommandManager.getCommands().get(this.tokens[0]);
    }

    public CommandArgument getArguments() {
        return null;
    }
}
