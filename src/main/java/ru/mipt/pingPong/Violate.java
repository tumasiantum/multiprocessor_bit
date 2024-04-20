package ru.mipt.pingPong;

public class Violate {
    private static volatile boolean pingTurn = true;

    public static void main(String[] args) {
        Thread pingThread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                while (!pingTurn) {
                    // Ждем своей очереди
                }
                System.out.println("PING - " + i);
                pingTurn = false; // Передаем ход другому потоку
            }
        });

        Thread pongThread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                while (pingTurn) {
                    // Ждем своей очереди
                }
                System.out.println("PONG - " + i);
                pingTurn = true; // Передаем ход другому потоку
            }
        });

        pingThread.start();
        pongThread.start();
    }
}

