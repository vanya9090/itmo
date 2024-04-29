package vanya9090.common.commands;

import java.io.Serializable;
import java.lang.reflect.Type;
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
    public Command(String name, String description, CommandArgument[] arguments) {
        this.name = name;
        this.description = description;
        this.arguments = arguments;
    }
    public CommandArgument[] getArguments(){return this.arguments;}
    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    abstract public Object[] apply(Map<String, Object> arg) throws Exception;
}
