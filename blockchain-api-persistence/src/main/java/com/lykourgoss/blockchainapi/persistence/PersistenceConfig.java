package com.lykourgoss.blockchainapi.persistence;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@AutoConfiguration
@EntityScan(basePackages = "com.lykourgoss.blockchainapi.persistence")
@EnableJpaRepositories(basePackages = "com.lykourgoss.blockchainapi.persistence")
@ComponentScan(basePackages = "com.lykourgoss.blockchainapi.persistence")
public class PersistenceConfig {
}
