package vanya9090.client.connection;


import com.google.common.primitives.Bytes;
import org.apache.commons.lang3.SerializationUtils;
import vanya9090.client.Client;
import vanya9090.common.util.ILogger;
import vanya9090.common.connection.Request;
import vanya9090.common.connection.Response;
import vanya9090.common.connection.ObjectIO;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;

public class UDPThreadClient{
    private final int BUFFER_SIZE = 1024;
    private final int DATA_SIZE = BUFFER_SIZE - 1;

    private final InetSocketAddress addr;
    private final DatagramSocket socket;
    private final ILogger logger = Client.logger;

    public UDPThreadClient(InetAddress address, int port) throws IOException {
        this.socket = new DatagramSocket();
        this.addr = new InetSocketAddress(address, port);
        logger.info("DatagramChannel подключен к " + addr);
    }


    public Response request(Request request) throws IOException, ClassNotFoundException {
        ByteBuffer buffer = ByteBuffer.wrap(ObjectIO.writeObject(request).toByteArray());
        DatagramPacket sendPacket = new DatagramPacket(buffer.array(), buffer.limit(), this.addr.getAddress(), this.addr.getPort());
        socket.send(sendPacket);

        byte[] buffer1 = new byte[4096];
        DatagramPacket packet = new DatagramPacket(buffer1, buffer1.length);
        socket.receive(packet);
        return (Response) ObjectIO.readObject(buffer1);
    }
}