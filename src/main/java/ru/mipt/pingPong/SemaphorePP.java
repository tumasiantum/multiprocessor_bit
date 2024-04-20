package ru.mipt.pingPong;


import java.util.concurrent.Semaphore;

public class SemaphorePP {
    private static Semaphore pingSemaphore = new Semaphore(1);
    private static Semaphore pongSemaphore = new Semaphore(0);
    private static int iteration = 1;

    public static void main(String[] args) {
        Thread pingThread = new Thread(SemaphorePP::ping);
        Thread pongThread = new Thread(SemaphorePP::pong);
        pingThread.start();
        pongThread.start();
    }

    private static void ping() {
        try {
            while (iteration <= 10) {
                pingSemaphore.acquire();
                System.out.println("PING " + iteration++);
                pongSemaphore.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void pong() {
        try {
            while (iteration <= 10) {
                pongSemaphore.acquire();
                System.out.println("PONG " + iteration++);
                pingSemaphore.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}