package com.lykourgoss.blockchainapi.example;

import com.lykourgoss.blockchainapi.benchmarking.sampler.GenericSampler;
import com.lykourgoss.blockchainapi.core.helpers.serializer.JsonSerializer;

public interface BlockchainConfig<T> {
    JsonSerializer serializer();
    GenericSampler<T> sampler();
}
