package vanya9090.server.connection;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Connection {
    private InetAddress addr;
    private int port, id;
    private DatagramSocket clientSocket;

    public Connection(DatagramSocket socket, InetAddress addr, int port, int id) {
        this.addr = addr;
        this.port = port;
        this.id = id;
        this.clientSocket = socket;
    }

//    public void send(byte[] data) {
//        DatagramPacket packet = new DatagramPacket(data, data.length, addr, port);
//        clientSocket.send(packet);
//    }
}
