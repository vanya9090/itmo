package vanya9090.client;

import vanya9090.client.cli.Runner;
import vanya9090.client.connection.UDPClient;
import vanya9090.common.connection.Request;
import vanya9090.common.connection.Response;
import vanya9090.common.util.ILogger;
import vanya9090.common.util.Logger;

import java.net.InetAddress;

public final class Client {
    public static ILogger logger = new Logger();
    private static final int PORT = 3547;

    public static void main(String[] args){
        try {
            UDPClient client = new UDPClient(InetAddress.getLocalHost(), PORT);
            Runner runner = new Runner(client);
//            Response response = client.request(new Request("remove_head", ""));
//            System.out.println(response.getMessage());
            runner.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
