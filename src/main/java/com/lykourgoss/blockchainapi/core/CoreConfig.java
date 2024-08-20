package com.lykourgoss.blockchainapi.core;

import com.lykourgoss.blockchainapi.core.miners.Miner;
import com.lykourgoss.blockchainapi.core.miners.MultiThreadMiner;
import com.lykourgoss.blockchainapi.core.miners.SingleThreadMiner;
import com.lykourgoss.blockchainapi.core.validators.Validator;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class CoreConfig {
    @Value("${validator.zeros}")
    private int VALIDATOR_ZEROS;

    @PostConstruct
    private void init(){
        Validator.INSTANCE.init(VALIDATOR_ZEROS);
    }

    @Bean
    protected Miner miner(){
        return new SingleThreadMiner();
    }
}
