package com.lykourgoss.blockchainapi.core.miners;

import com.lykourgoss.blockchainapi.core.Block;
import com.lykourgoss.blockchainapi.core.miners.interfaces.MultiThreadMiner;

public class CustomMultiThreadMiner implements MultiThreadMiner {
    @Override
    public void mineFor(Block block) {

    }

    @Override
    public void setup(int numOfThreads) {

    }

    @Override
    public void threadPartialMining(Block block, int start, int end) {

    }
}
