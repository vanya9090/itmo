package vanya9090.client;

import vanya9090.client.cli.Runner;
import vanya9090.client.connection.UDPClient;
import vanya9090.common.commands.Command;
import vanya9090.common.commands.CommandArgument;
import vanya9090.common.connection.Request;
import vanya9090.common.connection.Response;
import vanya9090.common.util.ILogger;
import vanya9090.common.util.Logger;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public final class Client {
    public static ILogger logger = new Logger();
    private static final int PORT = 3547;

    public static void main(String[] args){
        try {
            UDPClient client = new UDPClient(InetAddress.getLocalHost(), PORT);
            Response help = client.request(new Request("help", new CommandArgument()));
            HashMap<String, String> commands = (HashMap<String, String>) help.getBody()[0];
            System.out.println(commands);
            Runner runner = new Runner(client, commands);
            runner.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
