package vanya9090.server.commands.list;

import vanya9090.common.commands.Command;
import vanya9090.common.commands.CommandArgument;

import javax.lang.model.type.NullType;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class GetCommands extends Command {
    private final Map<String, Command> commands;

    public GetCommands(Map<String, Command> commands) {
        super("get_commands", "system util", new CommandArgument[]{});
        this.commands = commands;
    }
    @Override
    public Map<String, String>[] apply(Map<String, Object> args) {
        System.out.println(this.commands.values().
                stream().
                collect(Collectors.toMap(Command::getName, Command::getArguments)));
        return new Map[]{this.commands.values().
                stream().
                collect(Collectors.toMap(Command::getName, Command::getArguments))};
    }
}
