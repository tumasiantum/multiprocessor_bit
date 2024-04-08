package ru.mipt;

import java.util.concurrent.atomic.AtomicIntegerArray;

// Second barrier implementation
public class ArrayBarrier {
    private final int n;
    private final AtomicIntegerArray b;

    public ArrayBarrier(int n) {
        this.n = n;
        this.b = new AtomicIntegerArray(n);
    }

    public void foo(int threadId) {
        if (threadId == 0) {
            b.set(0, 1);
        } else {
            while (b.get(threadId - 1) != 1) {
                // Spin
            }
            b.set(threadId, 1);
            while (b.get(threadId + 1) != 2) {
                // Spin
            }
        }
    }

    public void bar(int threadId) {
        if (threadId == n - 1) {
            b.set(n - 1, 2);
        }
    }
}
