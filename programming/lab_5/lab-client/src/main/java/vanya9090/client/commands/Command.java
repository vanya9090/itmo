package vanya9090.client.commands;

import vanya9090.common.exceptions.*;

import java.io.FileNotFoundException;

public abstract class Command {
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

    abstract public void apply(String[] args) throws Exception;
}
