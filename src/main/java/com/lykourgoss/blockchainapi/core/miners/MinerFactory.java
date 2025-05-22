package com.lykourgoss.blockchainapi.core.miners;

import com.lykourgoss.blockchainapi.core.miners.interfaces.Miner;

public enum MinerFactory {

    INSTANCE;

    public Miner getMiner(MinerType minerType, Integer numOfThreads) {
        return switch (minerType) {
            case SINGLE_THREAD -> new SingleThreadMiner();
            case JAVA_API_MULTI_THREAD -> new JavaAPIMultiThreadMiner(numOfThreads);
            case JAVA_API_MULTI_THREAD_V2 -> new JavaAPIMultiThreadMinerV2(numOfThreads);
            case CUSTOM_MULTI_THREAD -> new CustomMultiThreadMiner(numOfThreads);
            default -> throw new IllegalArgumentException("Miner type:["+ minerType +"] is unknown.");
        };
    }

    public Miner getMiner(MinerType minerType) {
        return getMiner(minerType, 1);
    }
}
