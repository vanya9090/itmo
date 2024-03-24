package vanya9090.client;

import vanya9090.client.connection.UDPConnection;
import vanya9090.common.connection.Request;

import java.net.InetAddress;

public final class Client {
    public Client(InetAddress address, int port) {
    }
    public static void main(String[] args){
        try {
            UDPConnection connection = new UDPConnection();
            connection.request(new Request("some name"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
