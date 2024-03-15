package vanya9090.client.commands;

import vanya9090.client.commands.Command;
import vanya9090.client.utils.ILogger;

import java.util.Map;

public class Help extends Command {
    private final Map<String, Command> commands;
    private final ILogger logger;

    public Help(ILogger logger, Map<String, Command> commands) {
        super("help", "вывести справку по доступным командам");
        this.commands = commands;
        this.logger = logger;
    }

    @Override
    public void apply(String[] args) {
        for (Map.Entry<String, Command> entry : this.commands.entrySet()) {
            String key = entry.getKey();
            Command value = entry.getValue();
            this.logger.table(value.getName(), value.getDescription());
        }
    }
}
