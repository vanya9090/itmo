package com.vanya9090.client.commands;

import com.vanya9090.client.utils.ILogger;
import com.vanya9090.client.utils.Logger;

public class Exit extends Command {
    private final ILogger logger;

    public Exit(ILogger logger) {
        super("exit", "завершить программу (без сохранения в файл)");
        this.logger = logger;
    }

    @Override
    public void apply(String[] args) {
        this.logger.info("программа успешно завершена");
        System.exit(0);
    }
}
