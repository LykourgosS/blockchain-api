package com.lykourgoss.blockchainapi.benchmarking.states;

import com.lykourgoss.blockchainapi.core.Block;
import lombok.Getter;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
@Getter
public class BlockState {
    private Block block;

    @Setup(Level.Invocation)
    public void setupBlock(SpringBootState state){
        if (block != null){
            state.setPreviousHash(block.getHash());
        }
        block = new Block(state.getPreviousHash(), state.getSampler().getSample());
    }
}
