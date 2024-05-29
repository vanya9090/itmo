package vanya9090.server.connection;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SerializationException;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.SerializationUtils;

import vanya9090.server.commands.CommandExecutor;
import vanya9090.common.connection.Request;
import vanya9090.common.connection.Response;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * UDP обработчик запросов
 * @author maxbarsukov
 */
abstract class UDPServer {
    private static final int READ_POOL_SIZE = 4;

    private final InetSocketAddress addr;
    private final CommandExecutor commandExecutor;
    private final ExecutorService service;

    private boolean running = true;

    public UDPServer(InetSocketAddress addr, CommandExecutor commandExecutor) {
        this.addr = addr;
        this.commandExecutor = commandExecutor;
        this.service = Executors.newFixedThreadPool(READ_POOL_SIZE);
    }

    public InetSocketAddress getAddr() {
        return addr;
    }

    /**
     * Получает данные с клиента.
     * Возвращает пару из данных и адреса клиента
     */
    public abstract Pair<Byte[], SocketAddress> receiveData() throws IOException;

    /**
     * Отправляет данные клиенту
     */
    public abstract void sendData(byte[] data, SocketAddress addr) throws IOException;

    public abstract void connectToClient(SocketAddress addr) throws SocketException;

    public abstract void disconnectFromClient();
    public abstract void close();

    public void run() {
        service.submit(() -> {
            while (running) {
                System.out.println(1);
                Pair<Byte[], SocketAddress> dataPair;
                try {
                    dataPair = receiveData();
                } catch (Exception e) {
                    disconnectFromClient();
                    System.out.println(e);
                    continue;
                }

                var dataFromClient = dataPair.getKey();
                var clientAddr = dataPair.getValue();
                System.out.println(clientAddr);
                try {
                    connectToClient(clientAddr);
                } catch (Exception e) {
                    System.out.println(e);
                }

                Request request;
                try {
                    request = SerializationUtils.deserialize(ArrayUtils.toPrimitive(dataFromClient));
                } catch (SerializationException e) {
                    disconnectFromClient();
                    System.out.println(e);
                    continue;
                }

                new Thread(() -> {
                    Response response = null;
                    try {
                        response = CommandExecutor.execute(request);
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    var data = SerializationUtils.serialize(response);

                    new Thread(() -> {
                        try {
                            sendData(data, clientAddr);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }).start();
                }).start();

                disconnectFromClient();
            }
            close();
        });
    }

    public void stop() {
        running = false;
    }
}