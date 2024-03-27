//package vanya9090.server.handlers;
//
//import vanya9090.common.connection.Request;
//import vanya9090.common.connection.Response;
//import vanya9090.common.connection.Status;
//import vanya9090.common.commands.Command;
//import vanya9090.server.managers.CommandManager;
//
//public class CommandHandler {
//    private final CommandManager commandManager;
//
//    public CommandHandler(CommandManager commandManager) {
//        this.commandManager = commandManager;
//    }
//
//    public Response handle(Request request) {
//        try {
//            Command command = CommandManager.getCommands().get(request.getCommandName());
//            return new Response(command.apply(request.getArgument()), Status.OK);
//        } catch (Exception e) {
//            return new Response("error", Status.FORBIDDEN);
//        }
//    }
//}