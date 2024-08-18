package com.lykourgoss.blockchainapi.core.miners;

import com.lykourgoss.blockchainapi.core.Block;
import com.lykourgoss.blockchainapi.core.validators.Validator;

public class SingleThreadMiner implements Miner {

    @Override
    public void mineFor(Block block) {
        while (!Validator.INSTANCE.validate(block.getHash())) {
            block.recalculateNextHash(1);
        }
    }
}
