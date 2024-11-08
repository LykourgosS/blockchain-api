package com.lykourgoss.blockchainapi.benchmarking.states.miners;

import com.lykourgoss.blockchainapi.core.miners.MinerFactory;
import com.lykourgoss.blockchainapi.core.miners.MinerType;
import com.lykourgoss.blockchainapi.core.miners.interfaces.Miner;
import lombok.Getter;
import org.openjdk.jmh.annotations.*;

@State(Scope.Thread)
@Getter
public class MultiThreadState {
    private Miner javaAPIMultiThreadMiner;
    private Miner customMultiThreadMiner;

    @Param({"2", "4", "8", "16", "32"})
    public int numOfThreads;

    @Setup(Level.Trial)
    public void setup() {
        javaAPIMultiThreadMiner = MinerFactory.INSTANCE.getMiner(MinerType.JAVA_API_MULTI_THREAD, numOfThreads);
        customMultiThreadMiner = MinerFactory.INSTANCE.getMiner(MinerType.CUSTOM_MULTI_THREAD, numOfThreads);
    }
}
