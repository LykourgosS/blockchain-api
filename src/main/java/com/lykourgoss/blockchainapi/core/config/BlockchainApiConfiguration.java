package com.lykourgoss.blockchainapi.core.config;

import com.lykourgoss.blockchainapi.core.validators.Validator;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlockchainApiConfiguration {
    @Value("${validator.zeros}")
    private int VALIDATOR_ZEROS;

    @PostConstruct
    private void init(){
        Validator.INSTANCE.init(VALIDATOR_ZEROS);
    }
}
