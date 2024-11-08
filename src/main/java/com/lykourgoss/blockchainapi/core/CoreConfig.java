package com.lykourgoss.blockchainapi.core;

import com.lykourgoss.blockchainapi.core.miners.*;
import com.lykourgoss.blockchainapi.core.miners.interfaces.Miner;
import com.lykourgoss.blockchainapi.core.miners.interfaces.MultiThreadMiner;
import com.lykourgoss.blockchainapi.core.validators.Validator;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class CoreConfig {
    @Value("${validator.zeros}")
    private int VALIDATOR_ZEROS;

    @Value("${miner-type}")
    private MinerType MINER_TYPE;

    @Value("${multi-thread-miner.num-of-threads}")
    private int MULTI_THREAD_MINERS_NUM_OF_THREADS;

    @PostConstruct
    private void init(){
        Validator.INSTANCE.init(VALIDATOR_ZEROS);
    }

    @Bean
    protected Miner miner(){
        return MinerFactory.INSTANCE.getMiner(MINER_TYPE, MULTI_THREAD_MINERS_NUM_OF_THREADS);
    }
}
