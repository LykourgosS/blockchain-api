package com.lykourgoss.blockchainapi.core.miners;

import com.lykourgoss.blockchainapi.core.Block;
import com.lykourgoss.blockchainapi.core.helpers.threading.ExecutorServiceHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class JavaAPIMultiThreadMiner extends AbstractMultiThreadMiner {
    private final AtomicInteger nonce;
    private List<Future<?>> futures;
    private ExecutorService service;

    JavaAPIMultiThreadMiner(int numOfThreads) {
        super(numOfThreads);
        nonce = new AtomicInteger();
    }

    @Override
    protected void configureThreadCollection() {
        service = Executors.newFixedThreadPool(numOfThreads, r -> {
            Thread thread = new Thread(r);
            thread.setName("MinerThread-" + thread.threadId());
            return thread;
        });
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
        ExecutorServiceHelper.cancelFutures(futures);
    }

    @Override
    protected void terminate() {
        ExecutorServiceHelper.shutdownExecutor(service, 2, TimeUnit.MINUTES);
    }
}
