package com.lykourgoss.blockchainapi.core.helpers.serializer;

public interface Serializer<T> {
    String serialize(T t);

    T deserialize(String string);
}
