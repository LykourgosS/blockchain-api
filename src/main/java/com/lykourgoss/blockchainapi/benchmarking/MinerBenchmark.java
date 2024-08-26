package com.lykourgoss.blockchainapi.benchmarking;

import com.lykourgoss.blockchainapi.benchmarking.states.BlockState;
import com.lykourgoss.blockchainapi.benchmarking.states.miners.MultiThreadState;
import com.lykourgoss.blockchainapi.benchmarking.states.miners.SingleThreadState;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.SECONDS)
public class MinerBenchmark {
    public static void main(String[] args) throws Exception {
        new BenchmarkRunner(MinerBenchmark.class).run();
    }

    @Benchmark
    public void singleThreadMining(SingleThreadState singleThreadState, BlockState blockState){
        singleThreadState.getSingleThreadMiner().mineFor(blockState.getBlock());
    }

    @Benchmark
    @Threads(Threads.MAX)
    public void javaAPImultiThreadMining(MultiThreadState multiThreadState, BlockState blockState){
        multiThreadState.getJavaAPIMultiThreadMiner().mineFor(blockState.getBlock());
    }

//    @Benchmark
    public void customMultiThreadMining(MultiThreadState multiThreadState, BlockState blockState){
        multiThreadState.getCustomMultiThreadMiner().mineFor(blockState.getBlock());
    }
}
