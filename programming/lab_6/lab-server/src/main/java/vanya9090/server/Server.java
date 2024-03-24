package vanya9090.server;

import vanya9090.server.connection.ConnectionManager;
import vanya9090.server.connection.UDPConnection;
import vanya9090.server.commands.CommandExecutor;

import java.io.IOException;

public final class Server {
    public static void main(String[] args) throws IOException {
        ConnectionManager udpManager = new UDPConnection();
        udpManager.setRequestCallback(CommandExecutor::execute);
        udpManager.run();
    }
}
