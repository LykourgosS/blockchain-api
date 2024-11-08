package com.lykourgoss.blockchainapi.core.miners;

import com.lykourgoss.blockchainapi.core.Block;
import com.lykourgoss.blockchainapi.core.helpers.jsonizer.GsonJsonizer;
import com.lykourgoss.blockchainapi.core.miners.interfaces.MultiThreadMiner;
import com.lykourgoss.blockchainapi.core.validators.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class JavaAPIMultiThreadMiner extends AbstractMultiThreadMiner {
    private List<Future<?>> futures;
    private ExecutorService service;
    private final AtomicInteger nonce;

    public JavaAPIMultiThreadMiner(int numOfThreads) {
        super(numOfThreads);
        nonce = new AtomicInteger();
    }

    @Override
    protected void configureThreadCollection() {
        service = Executors.newFixedThreadPool(numOfThreads);
        futures = new ArrayList<>();
    }

    @Override
    protected int getNonce() {
        return nonce.get();
    }

    @Override
    protected void setNonce(int nonce) {
        this.nonce.set(nonce);
    }

    @Override
    protected void assignJobToThreads(Block block, int start, int end) {
        futures.add(service.submit(() -> threadPartialMining(block, start, end)));
    }

    @Override
    protected void interruptAllThreads() {
        for (Future<?> future : futures) {
//            if (!future.isDone()) {
                future.cancel(true);
//            }
        }
    }

    @Override
    protected void terminate() {
        service.shutdown();
        try {
            if (!service.awaitTermination(2, TimeUnit.MINUTES)) {
                service.shutdownNow();
            }
        } catch (InterruptedException e) {
            service.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
