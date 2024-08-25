package com.lykourgoss.blockchainapi.persistence;

import com.lykourgoss.blockchainapi.core.Block;
import com.lykourgoss.blockchainapi.reflection.Comparator;
import com.lykourgoss.blockchainapi.core.helpers.jsonizer.GsonJsonizer;
import com.lykourgoss.blockchainapi.core.mappers.BlockMapper;
import com.lykourgoss.blockchainapi.core.miners.interfaces.Miner;
import com.lykourgoss.blockchainapi.core.validators.Validator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlockService implements IService<Block, String> {
    private final BlockRepository repository;
    private final Miner miner;
    private final BlockMapper mapper;

    public BlockService(
            final BlockRepository repository,
            final Miner miner,
            final BlockMapper mapper
    ) {
        this.repository = repository;
        this.miner = miner;
        this.mapper = mapper;
    }

    public boolean validate(){
        return Validator.INSTANCE.validate(getAll());
    }

    public String getLastHash(){
        return getAll().isEmpty() ? "" : getAll().getLast().getHash();
    }

    public String toJson(){
        return GsonJsonizer.INSTANCE.toPrettyJson(getAll());
    }

    public Block addWithData(Object data){
        Block block = new Block(getLastHash(), data);
        miner.mineFor(block);
        return add(block);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public List<Block> getAllLike(Object data){
        return getAll().parallelStream()
                .filter(x-> Comparator.INSTANCE.compareCommonFieldsExcludingDefaultValues(x.getData(),data))
                .collect(Collectors.toList());
    }

    @Override
    public Block add(Block tBlock) {
        repository.save(mapper.toDatabaseEntity(tBlock));
        return tBlock;
    }

    @Override
    public Block get(String string) {
        return mapper.toApplicationEntity(repository.findById(string).orElseThrow());
    }

    @Override
    public List<Block> getAll() {
        return mapper.toApplicationEntityList(repository.findAll());
    }

    @Override
    public Block update(String string, Block tBlock) {
        throw new UnsupportedOperationException("In blockchain, the only thing that gets updated is your understanding of how nothing gets updated.");
    }

    @Override
    public Block delete(String string) {
        throw new UnsupportedOperationException("In blockchain, deleting blocks is like trying to unspill coffee from a shirt. Once it's there, it's part of the history!");
    }
}
