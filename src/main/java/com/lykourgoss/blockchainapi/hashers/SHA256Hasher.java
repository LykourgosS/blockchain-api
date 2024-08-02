package com.lykourgoss.blockchainapi.hashers;

import com.lykourgoss.blockchainapi.helpers.stringifier.Stringifier;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Hasher implements Hasher{
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
