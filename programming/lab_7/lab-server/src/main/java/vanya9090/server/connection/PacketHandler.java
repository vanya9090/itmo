//package vanya9090.server.connection;
//
//import vanya9090.common.connection.Request;
//
//import java.io.ByteArrayInputStream;
//import java.io.ObjectInputStream;
//import java.net.DatagramPacket;
//
//public class PacketHandler {
//    public static void process(DatagramPacket packet) {
//        try {
//            ByteArrayInputStream byteStream = new ByteArrayInputStream(packet.getData());
//            ObjectInputStream objectStream = new ObjectInputStream(byteStream);
//            Request request = (Request) objectStream.readObject();
//            forkJoinPool.execute(() -> {
//                try {
//                    lock.lock();
//                    response = this.requestCallback.call(request);
//                    lock.unlock();
//
//                    send(response, packet.getAddress(), packet.getPort());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
