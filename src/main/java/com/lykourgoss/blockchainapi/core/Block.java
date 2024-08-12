package com.lykourgoss.blockchainapi.core;

import com.lykourgoss.blockchainapi.hashers.SHA256Hasher;
import com.lykourgoss.blockchainapi.helpers.stringifier.Exclude;
import com.lykourgoss.blockchainapi.helpers.stringifier.Stringifier;
import com.lykourgoss.example.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
public class Block<T extends Blockable> {
    @Setter
    @Exclude
    @Id
    private String hash;
    private String previousHash;
    @OneToOne(targetEntity = Product.class)
    private T blockableObject;
    private long timestamp;
    @Setter
    private int nonce;

    public Block() {
    }

    public Block(String previousHash, T blockableObject) {
        this.hash = "";
        this.previousHash = previousHash;
        this.blockableObject = blockableObject;
        this.timestamp = Instant.now().toEpochMilli();
    }

    public String stringify() {
        return Stringifier.toString(this);
    }

    public String calculateHash() {
        return SHA256Hasher.INSTANCE.hash(stringify());
    }
}
