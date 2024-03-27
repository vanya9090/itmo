package vanya9090.client;

import vanya9090.common.connection.ObjectIO;
import vanya9090.common.connection.Request;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;

public class Test {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 9999;
    public static void main(String[] args) {
        try {
            DatagramChannel channel = DatagramChannel.open();
            channel.configureBlocking(false);

            ByteBuffer buffer;
            buffer = ByteBuffer.wrap(ObjectIO.writeObject(new Request("test", new String[]{"test"})).toByteArray());
            InetSocketAddress serverAddress = new InetSocketAddress(SERVER_IP, SERVER_PORT);
            channel.send(buffer, serverAddress);

            buffer.clear();
            channel.receive(buffer);
            buffer.flip();
            String response = new String(buffer.array(), 0, buffer.limit());
            System.out.println("Response from server: " + response);

            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
