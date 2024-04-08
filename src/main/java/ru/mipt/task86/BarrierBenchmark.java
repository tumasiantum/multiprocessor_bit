package ru.mipt;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
public class BarrierBenchmark {

    @Param({"20"}) // Количество потоков
    private int n;

    private TestAndTestAndSetBarrier testAndTestAndSetBarrier;
    private ArrayBarrier arrayBarrier;

    @Setup
    public void setup() {
        testAndTestAndSetBarrier = new TestAndTestAndSetBarrier(n);
        arrayBarrier = new ArrayBarrier(n);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void testTestAndTestAndSetBarrier() {
        testAndTestAndSetBarrier.foo();
        testAndTestAndSetBarrier.bar();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void testArrayBarrier() {
        long threadId = Thread.currentThread().getId();
        arrayBarrier.foo((int) threadId);
        arrayBarrier.bar((int) threadId);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BarrierBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(5)
                .measurementIterations(5)
                .build();

        new Runner(opt).run();
    }
}