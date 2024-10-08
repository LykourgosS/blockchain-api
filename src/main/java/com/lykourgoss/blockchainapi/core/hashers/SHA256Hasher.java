package com.lykourgoss.blockchainapi.core.hashers;

import com.lykourgoss.blockchainapi.core.helpers.stringifier.Stringifier;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public enum SHA256Hasher implements Hasher{
    INSTANCE;

    @Override
    public String hash(String stringToHash) {
        byte[] bytes;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(stringToHash.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return Stringifier.toHexString(bytes);
    }
}
