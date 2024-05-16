package vanya9090.server.commands;

import vanya9090.common.commands.Command;
import vanya9090.common.commands.CommandArgument;
import vanya9090.common.commands.CommandType;
import vanya9090.common.connection.Request;
import vanya9090.common.connection.Response;
import vanya9090.common.connection.Status;
import vanya9090.common.commands.CommandManager;
import vanya9090.common.models.User;
import vanya9090.server.Server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class CommandExecutor {

    static public void handleUser(User user) {

    }

    static public Response execute(Request request) throws Exception {
        try {
            if (request.getArgument() != null) {
                if (request.getArgument().containsKey("user")) {
                    System.out.println(request.getArgument().get("user"));
                }
            }
            Command command = CommandManager.getCommands().get(request.getCommandName());
            Object[] out = command.apply(request.getArgument());
            if (Objects.equals(command.getName(), "login") || Objects.equals(command.getName(), "signin")) {
                return new Response(out, Status.CREATED);
            }
            return new Response(out, Status.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new Response(String.valueOf(e), Status.FORBIDDEN);
        }
    }
}
