package com.lykourgoss.blockchainapi.benchmarking.sampler;

import org.jeasy.random.EasyRandom;

public class GenericSampler<T> implements Sampler<T> {
    private final EasyRandom easyRandom;

    private final Class<T> tClass;

    public GenericSampler(Class<T> tClass) {
        this.tClass = tClass;
        easyRandom = new EasyRandom();
    }

    @Override
    public T getSample() {
        return easyRandom.nextObject(tClass);
    }
}
