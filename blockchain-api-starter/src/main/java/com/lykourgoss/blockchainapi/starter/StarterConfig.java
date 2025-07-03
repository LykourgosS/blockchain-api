package com.lykourgoss.blockchainapi.starter;

import com.lykourgoss.blockchainapi.core.CoreConfig;
import com.lykourgoss.blockchainapi.persistence.PersistenceConfig;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({CoreConfig.class, PersistenceConfig.class})
public class StarterConfig {
}
