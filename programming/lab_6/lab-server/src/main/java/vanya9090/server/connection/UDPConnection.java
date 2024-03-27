package vanya9090.server.connection;

import vanya9090.common.connection.ObjectIO;
import vanya9090.common.connection.Request;
import vanya9090.common.connection.Response;
import vanya9090.common.connection.Status;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class UDPConnection extends ConnectionManager{
    InetSocketAddress clientAddress;
    int port = 3547;
    Selector selector;
    public UDPConnection() throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        channel.bind(new InetSocketAddress(port));
        channel.configureBlocking(false);

        selector = Selector.open();
        channel.register(selector, SelectionKey.OP_READ);

        this.clientAddress = null;
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
                        System.out.println("reading");

                        DatagramChannel client = (DatagramChannel) key.channel();
                        client.configureBlocking(false);

                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        this.clientAddress = (InetSocketAddress) client.receive(buffer);
                        buffer.flip();

                        ByteArrayInputStream bi = new ByteArrayInputStream(buffer.array());
                        ObjectInputStream oi = new ObjectInputStream(bi);
                        Request request = (Request) oi.readObject();
                        System.out.println(request.getCommandName());
                        response = this.requestCallback.call(request);
                        key.interestOps(SelectionKey.OP_WRITE | SelectionKey.OP_READ);
                        System.out.println(key.isWritable() + " " + key.isReadable() + " " + key.isValid());
                    } if (key.isWritable() && key.isValid()) {
                        System.out.println("writing");

                        DatagramChannel client = (DatagramChannel) key.channel();
                        client.configureBlocking(false);
                        System.out.println(response.getBody());
                        ByteBuffer buffer = ByteBuffer.wrap(ObjectIO.writeObject(response).toByteArray());
                        buffer.clear();
                        client.send(buffer, this.clientAddress);
                        client.register(selector, SelectionKey.OP_READ);
                        System.out.println(key.isWritable() + " " + key.isReadable() + " " + key.isValid());
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
