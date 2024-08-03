package com.lykourgoss.blockchainapi.miners;

import com.lykourgoss.blockchainapi.Block;
import com.lykourgoss.blockchainapi.Blockable;
import com.lykourgoss.blockchainapi.hashers.Hasher;
import com.lykourgoss.blockchainapi.hashers.SHA256Hasher;
import com.lykourgoss.blockchainapi.validators.Validator;

public class SingleThreadMiner implements Miner {

    private final Hasher hasher;

    public SingleThreadMiner() {
        this.hasher = new SHA256Hasher();;
    }

    @Override
    public <T extends Blockable> void mineFor(Block<T> block, int zeros) {
        while (!Validator.INSTANCE.validate(block.getHash())) {
            block.setNonce(block.getNonce() + 1);
            block.setHash(hasher.hash(block.stringify()));
        }
    }
}
