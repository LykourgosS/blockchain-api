package com.lykourgoss.blockchainapi.core.miners.interfaces;

import com.lykourgoss.blockchainapi.core.Block;

public interface Miner {
    void mineFor(Block block);
}
