package com.lykourgoss.blockchainapi.core.helpers.jsonizer;

public interface Jsonizer {
    String toJson(Object object);
    <T> T fromJson(String string);
}
