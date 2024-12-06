package com.lykourgoss.blockchainapi.core;

import com.lykourgoss.blockchainapi.core.miners.MinerType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "blockchain-api.core")
@Getter
@Setter
public class CoreProperties {
    private int validatorZeros;
    private MinerType minerType;
    private int multiThreadMinerNumOfThreads;
}
