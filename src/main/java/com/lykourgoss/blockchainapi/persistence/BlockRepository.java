package com.lykourgoss.blockchainapi.persistence;

import com.lykourgoss.blockchainapi.core.Block;
import com.lykourgoss.blockchainapi.core.Blockable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository<T extends Blockable> extends JpaRepository<Block<T>, String> {
}
