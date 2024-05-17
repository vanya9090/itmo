package vanya9090.server.utils;

public class test {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new MyThread().start();
        }
    }
}

class MyThread extends Thread {
    public void run() {
//        System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getPriority());
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}