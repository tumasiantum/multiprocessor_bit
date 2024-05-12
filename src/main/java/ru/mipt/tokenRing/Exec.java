package ru.mipt.tokenRing;

public class Exec {
    public static void main(String[] args) throws InterruptedException {
        TokenRingNetwork network = new TokenRingNetwork(2); // Change the number of nodes as needed
        network.start();

        // Example: sending a packet from node 0
        network.sendPacket(0, 11111);
        network.sendPacket(0, 22222);
        network.sendPacket(0, 33333);

        // Wait for some time to observe packet forwarding
        Thread.sleep(100);

        network.stop();
    }
}
