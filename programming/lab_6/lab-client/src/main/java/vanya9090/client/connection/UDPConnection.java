package vanya9090.client.connection;

import vanya9090.common.connection.ObjectIO;
import vanya9090.common.connection.Request;
import vanya9090.common.connection.Response;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;

public class UDPConnection extends Connection{
    private final InetSocketAddress inetAddress;
    int port = 3547;
    String address = "127.0.0.1";
    private final DatagramChannel channel;
    public UDPConnection() {
        this.inetAddress = new InetSocketAddress(address, port);
        try {
            this.channel = DatagramChannel.open().connect(inetAddress);
            this.channel.configureBlocking(false);
            System.out.println("DatagramChannel подключен к " + inetAddress);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Response request(Request request) throws Exception {
        ByteBuffer buffer = ByteBuffer.wrap(ObjectIO.writeObject(request).toByteArray());
        this.channel.send(buffer, this.inetAddress);

        ByteBuffer buffer1 = ByteBuffer.allocate(1024);
        this.channel.receive(buffer1);
        buffer1.flip();

        System.out.println(Arrays.toString(buffer1.array()));
//        buffer.flip();
//        System.out.println(Arrays.toString(buffer.array()));
//        String response = new String(buffer.array(), 0, buffer.limit());
//        System.out.println(response);

        return null;
    }
}
