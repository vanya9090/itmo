package vanya9090.server.connection;

import java.net.InetAddress;

public class Packet {
    private byte[] data;
    private InetAddress ip;
    private int port;
    private Connection conn;
    public Packet(byte[] data, InetAddress ip, int port) {
        this.data = data;
        this.ip = ip;
        this.port = port;

        this.conn = new Connection(null, ip, port, 10);
    }

    public byte[] getData() {return data;}
    public InetAddress getIp() {return ip;}
    public int getPort() {return port;}
}
