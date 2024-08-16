package com.lykourgoss.blockchainapi.persistence;

import com.lykourgoss.blockchainapi.core.BlockDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends JpaRepository<BlockDto, String> {
}
