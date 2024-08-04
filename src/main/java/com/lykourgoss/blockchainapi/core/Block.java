package com.lykourgoss.blockchainapi.core;

import com.lykourgoss.blockchainapi.hashers.SHA256Hasher;
import com.lykourgoss.blockchainapi.helpers.stringifier.Exclude;
import com.lykourgoss.blockchainapi.helpers.stringifier.Stringifier;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
public class Block<T extends Blockable> {
    @Setter
    @Exclude
    private String hash;
    private final String previousHash;
    private final T blockableObject;
    private final long timestamp;
    @Setter
    private int nonce;

    public Block(String previousHash, T blockableObject) {
        this.hash = "";
        this.previousHash = previousHash;
        this.blockableObject = blockableObject;
        this.timestamp = Instant.now().toEpochMilli();
    }

    public String stringify(){
        return Stringifier.toString(this);
    }

    public String calculateHash(){
        return new SHA256Hasher().hash(stringify());
    }
}
