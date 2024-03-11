package vanya9090.common.commands;

import com.vanya9090.client.utils.Logger;

import java.util.Map;

public class Help extends Command {
    private final Map<String, Command> commands;
    private final Logger logger;

    public Help(Logger logger, Map<String, Command> commands) {
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
