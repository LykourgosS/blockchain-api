package com.lykourgoss.blockchainapi.core.miners;

import com.lykourgoss.blockchainapi.core.Block;

public class MultiThreadMiner implements Miner{
    @Override
    public void mineFor(Block block) {
        System.out.println("MultiThreadMiner.mineFor:previousHash="+block.getPreviousHash());
//        block.setHash(String.valueOf(block.hashCode()));
    }
}
