package com.lykourgoss.blockchainapi.benchmarking.states.miners;

import com.lykourgoss.blockchainapi.benchmarking.states.SpringBootState;
import com.lykourgoss.blockchainapi.core.miners.CustomMultiThreadMiner;
import com.lykourgoss.blockchainapi.core.miners.JavaAPIMultiThreadMiner;
import lombok.Getter;
import org.openjdk.jmh.annotations.*;

@State(Scope.Thread)
@Getter
public class MultiThreadState {
    private JavaAPIMultiThreadMiner javaAPIMultiThreadMiner;
    private CustomMultiThreadMiner customMultiThreadMiner;

    @Param({"2", "4", "8", "16", "32"})
    public int numOfThreads;

    @Setup(Level.Trial)
    public void setup(SpringBootState state) {
        javaAPIMultiThreadMiner = state.getContext().getBean(JavaAPIMultiThreadMiner.class);
        javaAPIMultiThreadMiner.setup(numOfThreads);

        customMultiThreadMiner = state.getContext().getBean(CustomMultiThreadMiner.class);
        customMultiThreadMiner.setup(numOfThreads);
    }
}
