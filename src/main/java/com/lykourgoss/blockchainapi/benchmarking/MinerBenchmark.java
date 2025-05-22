package com.lykourgoss.blockchainapi.benchmarking;

import com.lykourgoss.blockchainapi.benchmarking.states.BlockState;
import com.lykourgoss.blockchainapi.benchmarking.states.forMiners.MultiThreadMinerState;
import com.lykourgoss.blockchainapi.benchmarking.states.forMiners.SingleThreadMinerState;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.SECONDS)
public class MinerBenchmark {
    public static void main(String[] args) throws Exception {
        BenchmarkRunnerSettings settings = new BenchmarkRunnerSettings(
                1,
                1,
                1,
                1,
                TimeUnit.MINUTES,
                3,
                1,
                TimeUnit.MINUTES
        );
        settings.calculateBenchmarkLastingTimeInMinutes();
        new BenchmarkRunner(MinerBenchmark.class, settings).run();
    }

    @Benchmark
    public void singleThreadMining(SingleThreadMinerState minerState, BlockState blockState){
        minerState.getSingleThreadMiner().mineFor(blockState.getBlock());
    }

    @Benchmark
    public void javaAPIMultiThreadMining(MultiThreadMinerState minerState, BlockState blockState){
        minerState.getJavaAPIMultiThreadMiner().mineFor(blockState.getBlock());
    }

    @Benchmark
    public void customMultiThreadMining(MultiThreadMinerState minerState, BlockState blockState){
        minerState.getCustomMultiThreadMiner().mineFor(blockState.getBlock());
    }
}
