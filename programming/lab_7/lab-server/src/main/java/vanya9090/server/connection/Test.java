package vanya9090.server.connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    private static final int BUFFER_SIZE = 1024;
    private static final int PORT = 8888;

    public static void main(String[] args) throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress(PORT));
        channel.configureBlocking(false);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(() -> {
            try {
                receiveData(channel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        executorService.submit(() -> {
            try {
                sendData(channel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private static void receiveData(DatagramChannel channel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

        while (true) {
            buffer.clear();
            SocketAddress clientAddress = channel.receive(buffer);
            if (clientAddress != null) {
                buffer.flip();
                System.out.println("Received: " + new String(buffer.array(), 0, buffer.limit()));
            }
        }
    }


    private static void sendData(DatagramChannel channel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        String message = "Hello from server";

        while (true) {
            buffer.clear();
            buffer.put(message.getBytes());
            buffer.flip();
            channel.send(buffer, new InetSocketAddress("localhost", PORT));
            System.out.println("Sent: " + message);
            try {
                Thread.sleep(1000); // Sending message every second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
