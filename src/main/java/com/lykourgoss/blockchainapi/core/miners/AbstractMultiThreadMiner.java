package com.lykourgoss.blockchainapi.core.miners;

import com.lykourgoss.blockchainapi.core.Block;
import com.lykourgoss.blockchainapi.core.miners.interfaces.MultiThreadMiner;
import com.lykourgoss.blockchainapi.core.validators.Validator;

public abstract class AbstractMultiThreadMiner implements MultiThreadMiner {
    protected int numOfThreads;

    public AbstractMultiThreadMiner(int numOfThreads) {
        this.numOfThreads = numOfThreads;
    }

    protected abstract void configureThreadCollection();

    protected abstract int getNonce();

    protected abstract void setNonce(int nonce);

    protected abstract void assignJobToThreads(Block block, int start, int end);

    protected abstract void interruptAllThreads();
    protected abstract void terminate();

    @Override
    public void mineFor(Block block) {
        setNonce(-1);
        configureThreadCollection();
        createAndStartThreads(block);
        terminate();
        block.recalculateNextHashBySetting(getNonce());
    }

    @Override
    public void threadPartialMining(Block block, int start, int end) {
        for (int i = start; i < end; i++) {
            if (Thread.currentThread().isInterrupted() ||
                    nonceFound()) {
                break;
            }
            block.recalculateNextHashBySetting(i);
            if (Validator.INSTANCE.validate(block)) {
                setNonce(i);
                interruptAllThreads();
                break;
            }
        }
    }

    private void createAndStartThreads(Block block) {
        configureThreadCollection();
        int chunkSize = Integer.MAX_VALUE / numOfThreads;
        for (int i = 0; i < numOfThreads; i++) {
            int start = i * chunkSize;
            int end = (i == numOfThreads - 1) ? Integer.MAX_VALUE : start + chunkSize;
            assignJobToThreads(block.getDeepCopy(), start, end);
        }
    }

    private boolean nonceFound() {
        return getNonce() >= 0;
    }
}
