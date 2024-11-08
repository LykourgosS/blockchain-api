package com.lykourgoss.blockchainapi.core.miners;

import com.lykourgoss.blockchainapi.core.Block;

import java.util.ArrayList;
import java.util.List;

public class CustomMultiThreadMiner extends AbstractMultiThreadMiner {
    private List<Thread> threads;
    private volatile int nonce;

    CustomMultiThreadMiner(int numOfThreads) {
        super(numOfThreads);
    }

    @Override
    protected void configureThreadCollection() {
        threads = new ArrayList<>();
    }

    @Override
    protected int getNonce() {
        return nonce;
    }

    @Override
    protected void setNonce(int nonce) {
        this.nonce=nonce;
    }

    @Override
    protected void assignJobToThreads(Block block, int start, int end) {
        Thread thread = new Thread(() -> threadPartialMining(block, start, end));
        threads.add(thread);
        thread.start();
    }

    @Override
    protected void interruptAllThreads() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    @Override
    protected void terminate() {

    }
}
