package com.lykourgoss.blockchainapi;

import com.lykourgoss.blockchainapi.miners.SingleThreadMiner;
import com.google.gson.GsonBuilder;
import com.lykourgoss.blockchainapi.validators.Validator;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Blockchain<T extends Blockable> {

    private final SingleThreadMiner miner;

    private final List<Block<T>> blocks;

    public Blockchain() {
        this.miner = new SingleThreadMiner();
        this.blocks = new ArrayList<>();
    }

    private boolean validate(){
        return Validator.INSTANCE.validate(this);
    }

    public String getLastHash(){
        return blocks.isEmpty() ? "" : blocks.getLast().getHash();
    }

    public Block<T> addBlock(T blockable){
        Block<T> block = new Block<>(getLastHash(), blockable);
        miner.mineFor(block, 3);
        blocks.add(block);
        return block;
    }

    public String toJson(){
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
