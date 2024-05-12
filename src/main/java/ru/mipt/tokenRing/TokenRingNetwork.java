package ru.mipt.tokenRing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TokenRingNetwork {
    private final int numNodes;
    private final TokenRingNode[] nodes;
    private ExecutorService executor;

    public TokenRingNetwork(int numNodes) {
        this.numNodes = numNodes;
        this.nodes = new TokenRingNode[numNodes];
        this.executor = Executors.newFixedThreadPool(numNodes);

        // Create nodes and form the ring
        for (int i = 0; i < numNodes; i++) {
            nodes[i] = new TokenRingNode(i);
        }

        for (int i = 0; i < numNodes; i++) {
            TokenRingNode prevNode = nodes[(i - 1 + numNodes) % numNodes];
            TokenRingNode nextNode = nodes[(i + 1) % numNodes];
            nodes[i].setNext(nextNode);
            nodes[i].setPrev(prevNode);
        }
    }

    public void start() {
        // Start all nodes
        for (TokenRingNode node : nodes) {
            executor.submit(node);
        }
    }

    public void stop() {
        // Stop all nodes
        executor.shutdownNow();
    }

    public void sendPacket(int senderId, int packet) throws InterruptedException {
        nodes[senderId].sendPacket(packet);
    }
}
