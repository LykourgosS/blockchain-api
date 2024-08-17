package com.lykourgoss.blockchainapi.core.miners;

import com.lykourgoss.blockchainapi.core.Block;
import com.lykourgoss.blockchainapi.core.hashers.SHA256Hasher;
import com.lykourgoss.blockchainapi.core.validators.Validator;

public class SingleThreadMiner implements Miner {

    @Override
    public void mineFor(Block block) {
        while (!Validator.INSTANCE.validate(block.getHash())) {
            block.setNonce(block.getNonce() + 1);
            block.setHash(SHA256Hasher.INSTANCE.hash(block.stringify()));
        }
    }
}
