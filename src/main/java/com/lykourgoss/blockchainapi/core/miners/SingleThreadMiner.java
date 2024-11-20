package com.lykourgoss.blockchainapi.core.miners;

import com.lykourgoss.blockchainapi.core.Block;
import com.lykourgoss.blockchainapi.core.miners.interfaces.Miner;
import com.lykourgoss.blockchainapi.core.validators.Validator;

class SingleThreadMiner implements Miner {

    SingleThreadMiner() {
    }

    @Override
    public void mineFor(Block block) {
        while (!Validator.INSTANCE.validate(block)) {
            block.recalculateNextHashByAdding(1);
        }
    }
}
