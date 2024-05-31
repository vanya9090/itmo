package vanya9090.server.connection;

import vanya9090.common.connection.ObjectIO;
import vanya9090.common.connection.Request;
import vanya9090.common.connection.Response;
import vanya9090.common.connection.Status;

import javax.swing.plaf.TableHeaderUI;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.security.interfaces.RSAKey;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class UDPThreadConnection extends ConnectionManager{
    int port = 17895;
    Selector selector;
    Request request;
    ReentrantLock lock;
    Map<SelectionKey, Object[]> keyResponseMap;
    public static ForkJoinPool forkJoinPool = new ForkJoinPool(1);


    public UDPThreadConnection() throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        channel.bind(new InetSocketAddress(port));
        channel.configureBlocking(false);

        selector = Selector.open();
        channel.register(selector, SelectionKey.OP_READ);

        this.lock = new ReentrantLock();
        keyResponseMap = new HashMap<>();
    }

    public void send(SelectionKey key, Response response, InetSocketAddress clientAddress) throws IOException {
        DatagramChannel client = (DatagramChannel) key.channel();
        client.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.wrap(ObjectIO.writeObject(response).toByteArray());
        buffer.clear();
        client.send(buffer, clientAddress);
    }

    @Override
    public void run(){
        Thread readThread = null, writeThread = null;
        while (true) {
            try {
                if (selector.select(100) == 0) continue;
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iter = keys.iterator();

                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    if (key.isReadable()) {
//                        if (writeThread != null) writeThread.join();
                        Runnable readTask = () -> {
                            try {
                                DatagramChannel client = (DatagramChannel) key.channel();
                                client.configureBlocking(false);
                                ByteBuffer buffer = ByteBuffer.allocate(4096);
                                InetSocketAddress clientAddress = (InetSocketAddress) client.receive(buffer);
                                buffer.flip();
                                ByteArrayInputStream bi = new ByteArrayInputStream(buffer.array());
                                ObjectInputStream oi = new ObjectInputStream(bi);
                                Request request = (Request) oi.readObject();
                                forkJoinPool.execute(() -> {
                                    try {
                                        lock.lock();
                                        Response response = this.requestCallback.call(request);
                                        keyResponseMap.put(key, new Object[]{response, clientAddress});
                                        lock.unlock();
                                        key.interestOps(SelectionKey.OP_WRITE);
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        };
                        readThread = new Thread(readTask);
                        readThread.start();
                        readThread.join();
                    } if (key.isWritable() && key.isValid()) {
//                        if (readThread != null) readThread.join();
                        Runnable writeTask = () -> {
                            try {
                                Object[] responseAndAddress = keyResponseMap.get(key);
                                send(key, (Response) responseAndAddress[0], (InetSocketAddress) responseAndAddress[1]);
                                key.interestOps(SelectionKey.OP_READ);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        };
                        writeThread = new Thread(writeTask);
                        writeThread.start();
                        writeThread.join();
                    }
                    iter.remove();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
