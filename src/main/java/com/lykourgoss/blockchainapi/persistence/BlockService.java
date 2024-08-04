package com.lykourgoss.blockchainapi.persistence;

import com.lykourgoss.blockchainapi.core.Blockable;
import org.springframework.stereotype.Service;

@Service
public class BlockService<T extends Blockable> {
    private final BlockRepository<T> repository;

    public BlockService(final BlockRepository<T> repository) {
        this.repository = repository;
    }
}
