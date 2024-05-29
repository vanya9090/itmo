package vanya9090.client.connection;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;

public class Test {

    private static final int BUFFER_SIZE = 1024;
    private static final int SERVER_PORT = 8888;

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("localhost");

        sendData(socket, serverAddress);
        receiveData(socket);
    }

    private static void sendData(DatagramSocket socket, InetAddress serverAddress) throws IOException {
        String message = "Hello from client";
        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
        socket.send(sendPacket);
        System.out.println("Sent: " + message);
    }

    private static void receiveData(DatagramSocket socket) throws IOException {
        byte[] receiveData = new byte[BUFFER_SIZE];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);
        String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Received: " + receivedMessage);
        socket.close();
    }
}
