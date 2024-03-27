package vanya9090.server.commands;

import vanya9090.common.commands.Command;
import vanya9090.common.connection.Request;
import vanya9090.common.connection.Response;
import vanya9090.common.connection.Status;
import vanya9090.server.managers.CommandManager;

public class CommandExecutor {
    static public Response execute(Request request) throws Exception {
        Command command = CommandManager.getCommands().get(request.getCommandName());
        Object out = command.apply(request.getArgument());

        return new Response(out, Status.OK);
    }
}
