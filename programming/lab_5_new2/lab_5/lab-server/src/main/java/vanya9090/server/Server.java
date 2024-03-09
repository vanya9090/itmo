package vanya9090.server;

import vanya9090.server.managers.JSONManager;

public final class Server {

    private Server() {
    }

    public static void main(String[] args) {
        JSONManager jsonManager = new JSONManager();
        System.out.println(jsonManager.read());
    }
}
