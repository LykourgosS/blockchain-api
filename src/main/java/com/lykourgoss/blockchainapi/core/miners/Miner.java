package com.lykourgoss.blockchainapi.core.miners;

import com.lykourgoss.blockchainapi.core.Block;

public interface Miner {
    void mineFor(Block block);
}
