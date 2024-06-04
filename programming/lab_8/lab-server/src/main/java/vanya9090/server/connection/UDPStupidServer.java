package vanya9090.server.connection;

import vanya9090.common.connection.Request;
import vanya9090.common.connection.Response;

import javax.crypto.spec.PSource;
import javax.xml.crypto.Data;
import java.io.*;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;


public class UDPStupidServer extends ConnectionManager{

    public DatagramSocket socket;
    public static ForkJoinPool forkJoinPool = new ForkJoinPool(2);
    DatagramPacket receivePacket;
    Response response;
    ReentrantLock lock;

    public UDPStupidServer() throws SocketException {
        this.socket = new DatagramSocket(17895);
        this.lock = new ReentrantLock();
    }

    public DatagramPacket read() throws IOException {
        new Thread(() -> {
            byte[] buffer = new byte[4096];
            receivePacket = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(receivePacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        return receivePacket;
    }

    public void send(Response response, InetAddress address, int port) {
        new Thread(() -> {
//            new Thread(() -> {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }).start();

            try {
                ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOutputStream);
                objectOutputStream.writeObject(response);
                objectOutputStream.flush();

                byte[] responseData = byteOutputStream.toByteArray();
                int dataSize = responseData.length;
                DatagramPacket responsePacket = new DatagramPacket(responseData, dataSize, address, port);
                socket.send(responsePacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                DatagramPacket packet = read();
                ByteArrayInputStream byteStream = new ByteArrayInputStream(packet.getData());
                ObjectInputStream objectStream = new ObjectInputStream(byteStream);
                Request request = (Request) objectStream.readObject();
                forkJoinPool.execute(() -> {
                    try {
                        lock.lock();
                        response = this.requestCallback.call(request);
                        lock.unlock();

                        send(response, packet.getAddress(), packet.getPort());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
