package com.lykourgoss.blockchainapi.example;

import com.lykourgoss.blockchainapi.core.helpers.serializer.JsonSerializer;
import com.lykourgoss.blockchainapi.core.helpers.serializer.Serializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExampleConfig {
    @Bean
    public JsonSerializer serializer(){
        return new JsonSerializer(Product.class);
    }
}
