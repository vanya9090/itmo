package vanya9090.client.connection;


import com.google.common.primitives.Bytes;
import org.apache.commons.lang3.SerializationUtils;
import vanya9090.client.Client;
import vanya9090.common.util.ILogger;
import vanya9090.common.connection.Request;
import vanya9090.common.connection.Response;
import vanya9090.common.connection.ObjectIO;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;

public class UDPClient {
    private final int PACKET_SIZE = 1024;
    private final int DATA_SIZE = PACKET_SIZE - 1;

    private final DatagramChannel client;
    private final InetSocketAddress addr;

    private final ILogger logger = Client.logger;

    public UDPClient(InetAddress address, int port) throws IOException {
        this.addr = new InetSocketAddress(address, port);
        this.client = DatagramChannel.open().bind(null).connect(addr);
        this.client.configureBlocking(false);
        logger.info("DatagramChannel подключен к " + addr);
    }


    public Response request(Request request) throws IOException, ClassNotFoundException {
        ByteBuffer buffer = ByteBuffer.wrap(ObjectIO.writeObject(request).toByteArray());
        while (buffer.hasRemaining()) {
            this.client.send(buffer, this.addr);
        }

        ByteBuffer buffer1 = ByteBuffer.allocate(4096);
        SocketAddress address = null;
        while (address == null) {
            address = this.client.receive(buffer1);
        }

        return (Response) ObjectIO.readObject(buffer1.array());
    }
}