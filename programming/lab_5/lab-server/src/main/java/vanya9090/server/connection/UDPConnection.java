package vanya9090.server.connection;

import vanya9090.common.connection.ObjectIO;
import vanya9090.common.connection.Request;
import vanya9090.common.connection.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class UDPConnection extends ConnectionManager{
    int port = 3547;
    Selector selector;
    public UDPConnection() throws IOException {
        InetSocketAddress address = new InetSocketAddress(InetAddress.getLocalHost(), port);

        DatagramChannel channel = DatagramChannel.open();
        channel.bind(address);
        channel.configureBlocking(false);

        selector = Selector.open();
        channel.register(selector, SelectionKey.OP_ACCEPT);
    }
    @Override
    public void run() {
        while (true) {
            Response response = null;
            try {
                if (selector.select(3000) == 0) continue;
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iter = keys.iterator();

                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    if (key.isReadable()) {
                        System.out.println("reading");

                        DatagramChannel client = (DatagramChannel) key.channel();
                        client.configureBlocking(false);

                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        client.read(buffer);

                        ByteArrayInputStream bi = new ByteArrayInputStream(buffer.array());
                        ObjectInputStream oi = new ObjectInputStream(bi);
                        Request request = (Request) oi.readObject();
                        response = this.requestCallback.call(request);
                        key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                    } else if (key.isWritable() && key.isValid()) {
                        System.out.println("writing");

                        DatagramChannel client = (DatagramChannel) key.channel();
                        client.configureBlocking(false);

                        ByteBuffer buffer = ByteBuffer.wrap(ObjectIO.writeObject(response).toByteArray());

                        while (buffer.hasRemaining()) {
                            client.write(buffer);
                        }

                        client.register(selector, SelectionKey.OP_READ);

                    }
                    iter.remove();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
