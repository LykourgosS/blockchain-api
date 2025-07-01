package com.lykourgoss.blockchainapi.core.miners.interfaces;

import com.lykourgoss.blockchainapi.core.Block;

public interface MultiThreadMiner extends Miner {
    void threadPartialMining(Block block, int start, int end);
}
