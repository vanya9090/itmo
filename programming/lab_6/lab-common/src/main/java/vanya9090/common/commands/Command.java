package vanya9090.common.commands;

import java.io.Serializable;

/**
 * абстрактный класс для всех команд
 *
 * @author vanya9090
 */
public abstract class Command implements Serializable {
    private final String name;
    private final String description;

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    abstract public Object[] apply(CommandArgument arg) throws Exception;
}
