package com.lykourgoss.blockchainapi.benchmarking.states;


import com.lykourgoss.blockchainapi.BlockchainApiApplication;
import com.lykourgoss.blockchainapi.benchmarking.sampler.GenericSampler;
import com.lykourgoss.blockchainapi.core.miners.Miner;
import com.lykourgoss.blockchainapi.persistence.BlockService;
import lombok.Getter;
import lombok.Setter;
import org.openjdk.jmh.annotations.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@State(Scope.Benchmark)
@Getter
public class SpringBootState {
    private ApplicationContext context;
    @Setter
    private String previousHash;
    private Miner miner;
    private GenericSampler<?> sampler;

    @Setup(Level.Trial)
    public void setupBeans() {
        context = new AnnotationConfigApplicationContext(BlockchainApiApplication.class);
        miner = context.getBean(Miner.class);
        sampler = context.getBean(GenericSampler.class);

        BlockService service = context.getBean(BlockService.class);
        previousHash = service.getLastHash();
    }

    @TearDown(Level.Trial)
    public void tearDown() {
        ((AnnotationConfigApplicationContext) context).close();
    }
}
