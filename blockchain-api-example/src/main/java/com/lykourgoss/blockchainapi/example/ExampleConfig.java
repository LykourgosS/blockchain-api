package com.lykourgoss.blockchainapi.example;

import com.lykourgoss.blockchainapi.core.BlockchainConfig;
import com.lykourgoss.blockchainapi.core.helpers.serializer.JsonSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExampleConfig implements BlockchainConfig<Product> {
    @Bean
    @Override
    public JsonSerializer serializer() {
        return new JsonSerializer(Product.class);
    }
}