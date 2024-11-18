package com.lykourgoss.blockchainapi.benchmarking.states.miners;

import com.lykourgoss.blockchainapi.core.miners.MinerFactory;
import com.lykourgoss.blockchainapi.core.miners.MinerType;
import com.lykourgoss.blockchainapi.core.miners.interfaces.Miner;
import lombok.Getter;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
@Getter
public class SingleThreadState {
    private Miner singleThreadMiner;

    @Setup(Level.Trial)
    public void setupMiner() {
        singleThreadMiner = MinerFactory.INSTANCE.getMiner(MinerType.SINGLE_THREAD);
    }
}
