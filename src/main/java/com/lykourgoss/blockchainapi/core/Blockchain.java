package com.lykourgoss.blockchainapi.core;

import com.lykourgoss.blockchainapi.helpers.jsonizer.GsonJsonizer;
import com.lykourgoss.blockchainapi.miners.Miner;
import com.lykourgoss.blockchainapi.validators.Validator;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Blockchain<T extends Blockable> {

    private final Miner miner;

    private final List<Block<T>> blocks;

    public Blockchain(Miner miner, int zeros) {
        this.miner = miner;
        this.blocks = new ArrayList<>();
    }

    public boolean validate(){
        return Validator.INSTANCE.validate(this);
    }

    public String getLastHash(){
        return blocks.isEmpty() ? "" : blocks.getLast().getHash();
    }

    public Block<T> addBlock(T blockable){
        Block<T> block = new Block<>(getLastHash(), blockable);
        miner.mineFor(block);
        blocks.add(block);
        return block;
    }

    public String toJson(){
        return GsonJsonizer.INSTANCE.toJson(getBlocks());
    }
}
