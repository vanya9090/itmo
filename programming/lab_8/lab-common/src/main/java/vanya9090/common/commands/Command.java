package vanya9090.common.commands;

import java.io.Serializable;
import java.util.Map;

/**
 * абстрактный класс для всех команд
 *
 * @author vanya9090
 */
public abstract class Command implements Serializable {
    private final String name;
    private final String description;
    private final CommandArgument[] arguments;
    private final CommandType commandType;
    public Command(String name, String description, CommandArgument[] arguments) {
        this.name = name;
        this.description = description;
        this.arguments = arguments;
        this.commandType = CommandType.CLIENT;
    }
    public Command(String name, String description, CommandArgument[] arguments, CommandType commandType) {
        this.name = name;
        this.description = description;
        this.arguments = arguments;
        this.commandType = commandType;
    }
    public CommandArgument[] getArguments(){return this.arguments;}
    public String getName() {
        return this.name;
    }
    public CommandType getCommandType() {
        return this.commandType;
    }

    public String getDescription() {
        return this.description;
    }

    abstract public Object[] apply(Map<String, Object> arg) throws Exception;
}
