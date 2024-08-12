package com.lykourgoss.blockchainapi.core.miners;

import com.lykourgoss.blockchainapi.core.Block;
import com.lykourgoss.blockchainapi.core.Blockable;

public interface Miner {
    <T extends Blockable> void mineFor(Block<T> block);
}
