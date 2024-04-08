package ru.mipt;

// First barrier implementation
public class TestAndTestAndSetBarrier {
    private int counter = 0;
    private final Object lock = new Object();
    private final int n;

    public TestAndTestAndSetBarrier(int n) {
        this.n = n;
    }

    public void foo() {
        synchronized (lock) {
            counter++;
        }
    }

    public void bar() {
        while (counter < n) {
            // Ожидание
        }
    }
}
