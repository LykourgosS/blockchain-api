package com.lykourgoss.blockchainapi.core;

import com.lykourgoss.blockchainapi.core.hashers.SHA256Hasher;
import com.lykourgoss.blockchainapi.core.helpers.stringifier.Exclude;
import com.lykourgoss.blockchainapi.core.helpers.stringifier.Stringifier;
import lombok.Data;

import java.time.Instant;


@Data
public class Block {
    @Exclude
    private String hash;
    private String previousHash;
    private Object data;
    private long timestamp;
    private int nonce;

    public Block(String previousHash, Object data) {
        this.hash = "";
        this.previousHash = previousHash;
        this.data = data;
        this.timestamp = Instant.now().toEpochMilli();
    }

    public String stringify() {
        return Stringifier.toString(this);
    }

    public String getCalculatedHash() {
        return SHA256Hasher.INSTANCE.hash(stringify());
    }

    private void addToNonce(int i) {
        nonce += i;
    }

    private void recalculateHash() {
        setHash(getCalculatedHash());
    }

    public void recalculateNextHash(int addToNonce){
        addToNonce(addToNonce);
        recalculateHash();
    }
}
