package com.lykourgoss.blockchainapi.core;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BlockDto {
    @Id
    private String hash;
    private String previousHash;
    private String stringData;
    private long timestamp;
    private int nonce;
}
