package com.lykourgoss.blockchainapi.core;

import com.lykourgoss.blockchainapi.core.helpers.serializer.JsonSerializer;

public interface BlockchainConfig<T> {
    JsonSerializer serializer();
}
