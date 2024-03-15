package vanya9090.client.connection;


import vanya9090.client.commands.Command;

import java.io.Serializable;

public class Request implements Serializable {
    private final String arguments;
    private final Command command;
    private final Integer client;

    public Request(Command command, String arguments) {
        this.command = command;
        this.arguments = arguments;
        this.client = (int) (Math.random() * 1e9);
    }

    public Integer getClient() {
        return client;
    }

    public String getName() {
        return this.command.getName();
    }

    public String getArguments() {
        return this.arguments;
    }
}