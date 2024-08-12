package com.lykourgoss.blockchainapi.core.miners;

import com.lykourgoss.blockchainapi.core.Block;
import com.lykourgoss.blockchainapi.core.Blockable;
import com.lykourgoss.blockchainapi.core.hashers.SHA256Hasher;
import com.lykourgoss.blockchainapi.core.validators.Validator;
import org.springframework.stereotype.Component;

@Component
public class SingleThreadMiner implements Miner {

    @Override
    public <T extends Blockable> void mineFor(Block<T> block) {
        while (!Validator.INSTANCE.validate(block.getHash())) {
            block.setNonce(block.getNonce() + 1);
            block.setHash(SHA256Hasher.INSTANCE.hash(block.stringify()));
        }
    }
}
