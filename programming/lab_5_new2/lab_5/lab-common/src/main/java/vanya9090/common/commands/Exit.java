package vanya9090.common.commands;

import com.vanya9090.client.utils.Logger;

public class Exit extends Command {
    private final Logger logger;

    public Exit(Logger logger) {
        super("exit", "завершить программу (без сохранения в файл)");
        this.logger = logger;
    }

    @Override
    public void apply(String[] args) {
        this.logger.info("программа успешно завершена");
        System.exit(0);
    }
}
