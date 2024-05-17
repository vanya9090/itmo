package vanya9090.server.connection;

import vanya9090.common.connection.ObjectIO;
import vanya9090.common.connection.Request;
import vanya9090.common.connection.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class UDPThreadConnection extends ConnectionManager{
    InetSocketAddress clientAddress;
    int port = 17895;
    Selector selector;
    public UDPThreadConnection() throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        channel.bind(new InetSocketAddress(port));
        channel.configureBlocking(false);

        selector = Selector.open();
        channel.register(selector, SelectionKey.OP_READ);
        this.clientAddress = null;
    }
    public Request read(SelectionKey key) throws Exception {
        DatagramChannel client = (DatagramChannel) key.channel();
        client.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        this.clientAddress = (InetSocketAddress) client.receive(buffer);
        buffer.flip();
        ByteArrayInputStream bi = new ByteArrayInputStream(buffer.array());
        ObjectInputStream oi = new ObjectInputStream(bi);
        Request request = (Request) oi.readObject();
//        client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ);
        return request;
    }

    public void send(SelectionKey key, Response response) throws IOException {
        DatagramChannel client = (DatagramChannel) key.channel();
        client.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.wrap(ObjectIO.writeObject(response).toByteArray());
        buffer.clear();
        client.send(buffer, this.clientAddress);
//        client.register(selector, SelectionKey.OP_READ);
    }

    @Override
    public void run() {
        AtomicReference<Response> response = new AtomicReference<>();
        Thread readThread;
        Thread writeThread;
        Map<Response, InetSocketAddress> responseAdressMap = new HashMap<>();
        while (true) {
            try {
                if (selector.select(1000) == 0) continue;
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iter = keys.iterator();

                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    if (key.isReadable()) {
                        Runnable readTask = () -> {
                            try {
                                Request request = read(key);
                                response.set(this.requestCallback.call(request));
                                key.interestOps(SelectionKey.OP_WRITE | SelectionKey.OP_READ);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        };
                        readThread = new Thread(readTask);
                        readThread.start();
//                        readThread.join();
                    } if (key.isWritable() && key.isValid()) {
                        Runnable writeTask = () -> {
                            try {
                                send(key, response.get());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            key.interestOps(SelectionKey.OP_READ);
                        };
                        writeThread = new Thread(writeTask);
                        writeThread.start();
//                        writeThread.join();
                    }
                    iter.remove();
                }
            } catch (IOException  e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
