package com.lykourgoss.blockchainapi.benchmarking.states.miners;

import com.lykourgoss.blockchainapi.benchmarking.states.SpringBootState;
import com.lykourgoss.blockchainapi.core.miners.SingleThreadMiner;
import lombok.Getter;
import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
@Getter
public class SingleThreadState {
    private SingleThreadMiner singleThreadMiner;

    @Setup(Level.Trial)
    public void setupMiner(SpringBootState state) {
        singleThreadMiner = state.getContext().getBean(SingleThreadMiner.class);
    }
}
