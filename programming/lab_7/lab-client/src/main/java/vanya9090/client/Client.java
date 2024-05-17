package vanya9090.client;

import vanya9090.client.cli.Runner;
import vanya9090.client.connection.UDPClient;
import vanya9090.client.connection.UDPThreadClient;
import vanya9090.client.forms.LoginForm;
import vanya9090.common.commands.Command;
import vanya9090.common.commands.CommandArgument;
import vanya9090.common.connection.Request;
import vanya9090.common.connection.Response;
import vanya9090.common.models.User;
import vanya9090.common.util.ILogger;
import vanya9090.common.util.Logger;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public final class Client {
    public static ILogger logger = new Logger();
    private static final int PORT = 17895;
    public static User user;

    public static void main(String[] args){
        try {
            UDPThreadClient client = new UDPThreadClient(InetAddress.getByName("localhost"), PORT);
//            UDPClient client = new UDPClient(InetAddress.getByName("192.168.10.80"), PORT);
//            user = new LoginForm(logger, new Scanner(System.in), false).create();
//            System.out.println(user.getLogin() + " " + user.getPassword());
            Response getCommands = client.request(new Request("get_commands", null));
            System.out.println(getCommands.getBody()[0]);
            HashMap<String, CommandArgument[]> commands = (HashMap<String, CommandArgument[]>) getCommands.getBody()[0];
            Runner runner = new Runner(client, commands);
            runner.run(System.in, logger, false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
