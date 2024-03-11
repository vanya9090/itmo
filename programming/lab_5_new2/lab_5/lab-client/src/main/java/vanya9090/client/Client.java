package vanya9090.client;

import vanya9090.common.commands.Command;
import vanya9090.common.commands.Read;

import vanya9090.server.*;

public final class Client {
    private Client() {
    }

    public static void main(String[] args) {
        //read collection from file
        Command read = new Read();
    }
}
