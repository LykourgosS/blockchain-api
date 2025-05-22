package com.lykourgoss.blockchainapi.core.miners;

import com.lykourgoss.blockchainapi.core.Block;
import com.lykourgoss.blockchainapi.core.helpers.threading.ExecutorServiceHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class JavaAPIMultiThreadMinerV2 extends AbstractMultiThreadMiner {
    private final AtomicInteger nonce;
    private List<Callable<Integer>> miningTasks;
    private ExecutorService service;

    JavaAPIMultiThreadMinerV2(int numOfThreads) {
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
        miningTasks = new ArrayList<>();
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
        miningTasks.add(() -> {
            threadPartialMining(block, start, end);
            return getNonce();
        });
    }

    @Override
    protected void interruptAllThreads() {
    }

    @Override
    protected void terminate() {
        try {
            service.invokeAny(miningTasks);
        }
        catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
        }
        finally {
            ExecutorServiceHelper.shutdownExecutor(service, 0, TimeUnit.MINUTES);
        }
    }
}
