package com.lykourgoss.blockchainapi.benchmarking.states;

import ch.qos.logback.classic.Logger;
import com.lykourgoss.blockchainapi.benchmarking.sampler.GenericSampler;
import com.lykourgoss.blockchainapi.example.BlockchainApiApplication;
import com.lykourgoss.blockchainapi.persistence.BlockService;
import lombok.Getter;
import lombok.Setter;
import org.openjdk.jmh.annotations.*;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@State(Scope.Benchmark)
@Getter
public class SpringBootState {
    private ApplicationContext context;
    @Setter
    private String previousHash;
    private GenericSampler<?> sampler;

    @Setup(Level.Trial)
    public void setupBeans() {
        ((Logger) LoggerFactory.getLogger("org.springframework")).setLevel(ch.qos.logback.classic.Level.WARN);
        ((Logger) LoggerFactory.getLogger("org.hibernate")).setLevel(ch.qos.logback.classic.Level.WARN);
        ((Logger) LoggerFactory.getLogger("com.zaxxer.hikari")).setLevel(ch.qos.logback.classic.Level.WARN);

        context = new AnnotationConfigApplicationContext(BlockchainApiApplication.class);
        sampler = context.getBean(GenericSampler.class);
        previousHash = context.getBean(BlockService.class).getLastHash();
    }

    @TearDown(Level.Trial)
    public void tearDown() {
        ((AnnotationConfigApplicationContext) context).close();
    }
}
