package ru.mipt.pingPong;

public class Synchronized {
    private static final Object lock = new Object();
    private static volatile boolean isPingTurn = true;
    private static int iteration = 1;

    public static void main(String[] args) {
        Thread pingThread = new Thread(Synchronized::ping);
        Thread pongThread = new Thread(Synchronized::pong);
        pingThread.start();
        pongThread.start();
    }

    private static void ping() {
        try {
            while (iteration <= 10) {
                synchronized (lock) {
                    while (!isPingTurn) {
                        lock.wait();
                    }
                    System.out.println("PING " + iteration++);
                    isPingTurn = false;
                    lock.notify();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void pong() {
        try {
            while (iteration <= 10) {
                synchronized (lock) {
                    while (isPingTurn) {
                        lock.wait();
                    }
                    System.out.println("PONG " + iteration++);
                    isPingTurn = true;
                    lock.notify();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
