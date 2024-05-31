//package vanya9090.client.cli;
//
//import vanya9090.client.Client;
//import vanya9090.client.connection.UDPClient;
//import vanya9090.client.forms.HumanBeingForm;
//import vanya9090.client.forms.LoginForm;
//import vanya9090.common.commands.CommandArgument;
//import vanya9090.common.commands.CommandType;
//import vanya9090.common.exceptions.AccessException;
//import vanya9090.common.exceptions.EmptyFieldException;
//import vanya9090.common.models.HumanBeing;
//import vanya9090.common.models.User;
//import vanya9090.common.validators.Validator;
//
//import java.lang.ref.Cleaner;
//import java.util.Map;
//
//public class GUIRunner {
//    private final UDPClient udpClient;
//
//    public GUIRunner(UDPClient udpClient) {
//        this.udpClient = udpClient;
//    }
//
//    public void handleCommand(String commandName) {
//        CommandArgument[] neededArgs = Client.commands.get(commandName);
//        for (CommandArgument commandArgument : neededArgs) {
//            if (commandArgument.getType() == HumanBeing.class){
//                field = new HumanBeingForm(logger, scanner, isExecute).create();
//            } else if(commandArgument.getType() == User.class){
//                field = new LoginForm(logger, scanner, isExecute).create();
//            } else {
//                try {
//                    field = handlers.get(commandArgument.getType().getSimpleName()).handle(arg[1], commandArgument.getName());
//                } catch (ArrayIndexOutOfBoundsException e){
//                    throw new EmptyFieldException(commandArgument.getName());
//                }
//                Validator<Object> validator = (Validator<Object>) validators.get(commandArgument.getName());
//                if (!validator.validate(field)) System.out.println("валидация не прошла!!!!!!!");
//            }
//            argsMap.put(commandArgument.getName(), field);
//        }
//        return argsMap;
//    }
//
//    public Map<String, Object> createDialog(String commandName) {
//        CommandArgument[] commandArguments = Client.commands.get(commandName);
//        System.out.println(commandArguments);
//        return
//    }
//}
