package vanya9090.client.connection;

import vanya9090.common.connection.Request;
import vanya9090.common.connection.Response;
import vanya9090.common.connection.ObjectIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class UDPConnection extends Connection{
    int port = 3547;
    String address = "127.0.0.1";
    private final DatagramChannel socket;
    public UDPConnection() {
        InetSocketAddress inetAddress = new InetSocketAddress(address, port);
        try {
            this.socket = DatagramChannel.open().connect(inetAddress);
            this.socket.configureBlocking(false);
            System.out.println("DatagramChannel подключен к " + inetAddress);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Response request(Request request) throws Exception {
        ByteBuffer buffer = ByteBuffer.wrap(ObjectIO.writeObject(request).toByteArray());

        while (buffer.hasRemaining()) {
            socket.write(buffer);
        }

        buffer = ByteBuffer.allocate(100000);

        int bytesRead = socket.read(buffer);
        while (bytesRead == -1) {
            bytesRead = socket.read(buffer);
        }

        try {
            return (Response) ObjectIO.readObject(buffer.array());
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
