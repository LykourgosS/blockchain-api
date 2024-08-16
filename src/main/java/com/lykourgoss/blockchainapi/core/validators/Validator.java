package com.lykourgoss.blockchainapi.core.validators;

import com.lykourgoss.blockchainapi.core.Block;

import java.util.List;

public enum Validator {
    INSTANCE;

    private String validPrefix;

    public void init(int zeros) {
        this.validPrefix = "0".repeat(zeros);
    }

    public Boolean validate(String hash) {
        return hash.startsWith(validPrefix);
    }

    public Boolean validate(Block block) {
        if (!validate(block.getHash()))
            return false;
        if (!block.getHash().equals(block.calculateHash()))
            return false;
        return true;
    }

    public Boolean validate(Block current, Block previous) {
        if (!validate(current))
            return false;
        if (!current.getPreviousHash().equals(previous.getHash()))
            return false;
        return true;
    }

    public boolean validate(List<Block> blocks) {
        for (int i = 1; i < blocks.size(); i++) {
            Block current = blocks.get(i);
            Block previous = blocks.get(i - 1);
            if (!validate(current, previous))
                return false;
        }
        return true;
    }
}
