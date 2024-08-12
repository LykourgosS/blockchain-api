package com.lykourgoss.blockchainapi.validators;

import com.lykourgoss.blockchainapi.core.Block;
import com.lykourgoss.blockchainapi.core.Blockable;

public enum Validator {
    INSTANCE;

    private String validPrefix;

    public void init(int zeros) {
        this.validPrefix = "0".repeat(zeros);
    }

    public Boolean validate(String hash) {
        return hash.startsWith(validPrefix);
    }

    public <T extends Blockable> Boolean validate(Block<T> block) {
        if (!validate(block.getHash()))
            return false;
        if (!block.getHash().equals(block.calculateHash()))
            return false;
        return true;
    }

    public <T extends Blockable> Boolean validate(Block<T> current, Block<T> previous) {
        if (!validate(current))
            return false;
        if (!current.getPreviousHash().equals(previous.getHash()))
            return false;
        return true;
    }

    public <T extends Blockable> boolean validate(Blockchain<T> blockchain) {
        for (int i = 1; i < blockchain.getBlocks().size(); i++) {
            Block<T> current = blockchain.getBlocks().get(i);
            Block<T> previous = blockchain.getBlocks().get(i - 1);
            if (!validate(current, previous))
                return false;
        }
        return true;
    }
}
