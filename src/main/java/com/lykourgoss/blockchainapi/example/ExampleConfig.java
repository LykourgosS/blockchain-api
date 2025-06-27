package com.lykourgoss.blockchainapi.example;

import com.lykourgoss.blockchainapi.benchmarking.sampler.GenericSampler;
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

    @Bean
    @Override
    public GenericSampler<Product> sampler() {
        return new GenericSampler<>(Product.class);
    }
}