package vanya9090.server.commands;

import vanya9090.common.connection.Request;
import vanya9090.common.connection.Response;
import vanya9090.server.managers.CommandManager;

public class CommandExecutor {
    static public Response execute(Request request) {
        Command command = CommandManager.getCommands().get(request.getCommandName());
        return new Response().withMessage("some message");
    }
}
