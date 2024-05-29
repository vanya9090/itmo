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
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class UDPConnection extends ConnectionManager{
    InetSocketAddress clientAddress;
    int port = 17895;
    Selector selector;
    public UDPConnection() throws IOException {
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
        return request;
    }

    public void send(SelectionKey key, Response response) throws IOException {
        DatagramChannel client = (DatagramChannel) key.channel();
        client.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.wrap(ObjectIO.writeObject(response).toByteArray());
        buffer.clear();
        client.send(buffer, this.clientAddress);
        client.register(selector, SelectionKey.OP_READ);
    }

    @Override
    public void run() {
        Response response = null;
        while (true) {
            try {
                if (selector.select(3000) == 0) continue;
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iter = keys.iterator();

                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    if (key.isReadable()) {
                        Request request = read(key);
                        response = this.requestCallback.call(request);
                        key.interestOps(SelectionKey.OP_WRITE | SelectionKey.OP_READ);
                    } if (key.isWritable() && key.isValid()) {
                        send(key, response);
                    }
                    iter.remove();
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
