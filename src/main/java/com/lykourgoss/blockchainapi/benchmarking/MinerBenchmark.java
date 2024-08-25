package com.lykourgoss.blockchainapi.benchmarking;

import com.lykourgoss.blockchainapi.benchmarking.states.BlockState;
import com.lykourgoss.blockchainapi.benchmarking.states.SpringBootState;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class MinerBenchmark {
    public static void main(String[] args) throws Exception {
        new BenchmarkRunner(MinerBenchmark.class).run();
    }

    @Benchmark
    public void singleThreadMining(SpringBootState springBootState, BlockState blockState){
        springBootState.getSingleThreadMiner().mineFor(blockState.getBlock());
    }

    @Benchmark
    public void multiThreadMining(SpringBootState springBootState, BlockState blockState){
        springBootState.getJavaAPIMultiThreadMiner().mineFor(blockState.getBlock());
    }
}
