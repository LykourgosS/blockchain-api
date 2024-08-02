package com.lykourgoss.blockchainapi.miners;

import com.lykourgoss.blockchainapi.Block;
import com.lykourgoss.blockchainapi.Blockable;

public interface Miner {
    <T extends Blockable> void mineFor(Block<T> block, int zeros);
}
