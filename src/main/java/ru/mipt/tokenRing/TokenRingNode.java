package ru.mipt.tokenRing;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TokenRingNode implements Runnable {
    private final int id;
    private TokenRingNode prev;
    private TokenRingNode next;
    private BlockingQueue<Integer> packetQueue;

    public TokenRingNode(int id) {
        this.id = id;
        this.packetQueue = new LinkedBlockingQueue<>();
    }

    public void setPrev(TokenRingNode prev) {
        this.prev = prev;
    }

    public void setNext(TokenRingNode next) {
        this.next = next;
    }

    public void sendPacket(int packet) throws InterruptedException {
        packetQueue.put(packet);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                int packet = packetQueue.take(); // Wait for a packet to arrive
                processPacket(packet);
                next.sendPacket(packet); // Forward the packet to the next node
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void processPacket(int packet) {
        // Process the received packet
        System.out.println("Node " + id + " received packet: " + packet);
    }
}
