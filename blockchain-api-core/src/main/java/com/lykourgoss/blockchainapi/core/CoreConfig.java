package com.lykourgoss.blockchainapi.core;

import com.lykourgoss.blockchainapi.core.miners.MinerFactory;
import com.lykourgoss.blockchainapi.core.miners.interfaces.Miner;
import com.lykourgoss.blockchainapi.core.validators.Validator;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@AutoConfiguration
@ComponentScan(basePackages = "com.lykourgoss.blockchainapi.core")
@EnableConfigurationProperties(CoreProperties.class)
public class CoreConfig {

    private final CoreProperties coreProperties;

    public CoreConfig(CoreProperties coreProperties) {
        this.coreProperties = coreProperties;
    }

    @PostConstruct
    private void init(){
        Validator.INSTANCE.init(coreProperties.getValidatorZeros());
    }

    @Bean
    protected Miner miner(){
        return MinerFactory.INSTANCE.getMiner(coreProperties.getMinerType(), coreProperties.getMultiThreadMinerNumOfThreads());
    }
}
