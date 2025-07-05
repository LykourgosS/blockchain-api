package com.lykourgoss.blockchainapi.benchmarking.states;

import com.lykourgoss.blockchainapi.benchmarking.sampler.GenericSampler;
import com.lykourgoss.blockchainapi.core.validators.Validator;
import com.lykourgoss.blockchainapi.example.Product;
import lombok.Getter;
import lombok.Setter;
import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
@Getter
public class MinerSettingsState {
    @Setter
    private String previousHash;
    private GenericSampler<?> sampler;

    @Setup(Level.Trial)
    public void setupBeans() {
        Validator.INSTANCE.init(5);
        previousHash = "";
        sampler = new GenericSampler<>(Product.class);
    }
}
