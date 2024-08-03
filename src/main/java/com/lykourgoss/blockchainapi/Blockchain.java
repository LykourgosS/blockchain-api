package com.lykourgoss.blockchainapi;

import com.lykourgoss.blockchainapi.miners.SingleThreadMiner;
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

    private void addBlock(Block<T> block){
        blocks.add(block);
    }

    public Block<T> addBlock(T blockable){
        String previousHash = blocks.isEmpty() ? "" : blocks.getLast().getHash();
        Block<T> block = new Block<>(previousHash, blockable);
        miner.mineFor(block, 3);
        addBlock(block);
        return block;
    }
}
