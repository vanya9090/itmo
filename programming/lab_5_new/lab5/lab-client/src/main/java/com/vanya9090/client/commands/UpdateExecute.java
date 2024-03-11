package com.vanya9090.client.commands;

import com.vanya9090.client.managers.CollectionManager;
import com.vanya9090.client.utils.Logger;

import java.util.Scanner;

public class UpdateExecute extends Command{
    public UpdateExecute(Logger logger, CollectionManager collectionManager) {
        super("updarte", "dsf");
    }

    @Override
    public void apply(String[] args) {

    }

    public void apply(String[] tokens, Scanner fileReader) {
    }
}
