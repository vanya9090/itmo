package vanya9090.server;

import vanya9090.server.managers.CollectionManager;
import vanya9090.server.managers.JSONManager;

public final class Server {

    private Server() {
    }

    public static void main(String[] args) {
        Logger logger = new Logger();
        CollectionManager collectionManager = new CollectionManager(logger);
        JSONManager jsonManager = new JSONManager();
        System.out.println(jsonManager.read());

    }
}
