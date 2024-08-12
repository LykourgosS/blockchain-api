package com.lykourgoss.blockchainapi.persistence;

import com.lykourgoss.blockchainapi.core.Block;
import com.lykourgoss.blockchainapi.core.Blockable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockService<T extends Blockable> implements IService<Block<T>, String> {
    private final BlockRepository<T> repository;

    public BlockService(final BlockRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public Block<T> add(Block<T> tBlock) {
        repository.save(tBlock);
        return tBlock;
    }

    @Override
    public Block<T> get(String string) {
        return repository.findById(string).orElseThrow();
    }

    @Override
    public List<Block<T>> getAll() {
        return repository.findAll();
    }

    @Override
    public Block<T> update(String string, Block<T> tBlock) {
        throw new UnsupportedOperationException("In blockchain, the only thing that gets updated is your understanding of how nothing gets updated.");
    }

    @Override
    public Block<T> delete(String string) {
        throw new UnsupportedOperationException("In blockchain, deleting blocks is like trying to unspill coffee from a shirt. Once it’s there, it’s part of the history!");
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}
