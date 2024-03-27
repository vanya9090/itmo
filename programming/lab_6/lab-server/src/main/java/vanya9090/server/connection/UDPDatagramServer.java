package vanya9090.server.connection;

import com.google.common.primitives.Bytes;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import vanya9090.server.handlers.CommandHandler;
import vanya9090.server.Server;
import vanya9090.common.util.ILogger;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class UDPDatagramServer extends UDPServer {
    private final int PACKET_SIZE = 1024;
    private final int DATA_SIZE = PACKET_SIZE - 1;

    private final DatagramSocket datagramSocket;

    private final ILogger logger = Server.logger;

    public UDPDatagramServer(InetAddress address, int port, CommandHandler commandHandler) throws SocketException {
        super(new InetSocketAddress(address, port), commandHandler);
        this.datagramSocket = new DatagramSocket(getAddr());
        this.datagramSocket.setReuseAddress(true);
    }

    @Override
    public Pair<Byte[], SocketAddress> receiveData() throws IOException {
        boolean received = false;
        byte[] result = new byte[0];
        SocketAddress addr = null;

        while(!received) {
            byte[] data = new byte[PACKET_SIZE];

            DatagramPacket dp = new DatagramPacket(data, PACKET_SIZE);
            datagramSocket.receive(dp);

            addr = dp.getSocketAddress();
            logger.info("Получено \"" + new String(data) + "\" от " + dp.getAddress());
            logger.info("Последний байт: " + data[data.length - 1]);

            if (data[data.length - 1] == 1) {
                received = true;
                logger.info("Получение данных от " + dp.getAddress() + " окончено");
            }
            result = Bytes.concat(result, Arrays.copyOf(data, data.length - 1));
        }
        return new ImmutablePair<>(ArrayUtils.toObject(result), addr);
    }

    @Override
    public void sendData(byte[] data, SocketAddress addr) throws IOException {
        byte[][] ret = new byte[(int)Math.ceil(data.length / (double)DATA_SIZE)][DATA_SIZE];

        int start = 0;
        for(int i = 0; i < ret.length; i++) {
            ret[i] = Arrays.copyOfRange(data, start, start + DATA_SIZE);
            start += DATA_SIZE;
        }

        logger.info("Отправляется " + ret.length + " чанков...");

        for(int i = 0; i < ret.length; i++) {
            byte[] chunk = ret[i];
            if (i == ret.length - 1) {
                byte[] lastChunk = Bytes.concat(chunk, new byte[]{1});
                DatagramPacket dp = new DatagramPacket(lastChunk, PACKET_SIZE, addr);
                datagramSocket.send(dp);
                logger.info("Последний чанк размером " + chunk.length + " отправлен на сервер.");
            } else {
                DatagramPacket dp = new DatagramPacket(ByteBuffer.allocate(PACKET_SIZE).put(chunk).array(), PACKET_SIZE, addr);
                datagramSocket.send(dp);
                logger.info("Чанк размером " + chunk.length + " отправлен на сервер.");
            }
        }

        logger.info("Отправка данных завершена");
    }

    @Override
    public void connectToClient(SocketAddress addr) throws SocketException {
        datagramSocket.connect(addr);
    }

    @Override
    public void disconnectFromClient() {
        datagramSocket.disconnect();
    }

    @Override
    public void close() {
        datagramSocket.close();
    }
}