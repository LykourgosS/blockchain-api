package com.lykourgoss.blockchainapi.validators;

import com.lykourgoss.blockchainapi.Block;
import com.lykourgoss.blockchainapi.Blockable;

public enum Validator {
    INSTANCE;

    private String validPrefix;

    public void Initialize(int zeros) {
        this.validPrefix = "0".repeat(zeros);
    }

    public Boolean validate(String hash){
        return hash.startsWith(validPrefix);
    }

    public <T extends Blockable> Boolean validate(Block<T> current, Block<T> previous){
        if (!current.getHash().equals(current.calculateHash()))
            return false;
        if (!validate(current.getHash()))
            return false;
        if (!current.getHash().equals(previous.getHash()))
            return false;
        return true;
    }
}
