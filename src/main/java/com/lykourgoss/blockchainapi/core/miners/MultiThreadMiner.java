package com.lykourgoss.blockchainapi.core.miners;

import com.lykourgoss.blockchainapi.core.Block;
import com.lykourgoss.blockchainapi.core.helpers.jsonizer.GsonJsonizer;
import com.lykourgoss.blockchainapi.core.validators.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadMiner implements Miner {
    private int numOfThreads;
    private AtomicInteger nonce;
    private List<Future<?>> futures;
    private ExecutorService service;

    public void setup(int numOfThreads) {
        this.numOfThreads = numOfThreads;
    }

    private void reset() {
        nonce = new AtomicInteger(-1);
        service = Executors.newFixedThreadPool(numOfThreads);
        futures = new ArrayList<>();
    }

    private void createAndStartThreads(Block block) {
        int chunkSize = Integer.MAX_VALUE / numOfThreads;
        for (int i = 0; i < numOfThreads; i++) {
            int start = i * chunkSize;
            int end = (i == numOfThreads - 1) ? Integer.MAX_VALUE : start + chunkSize;
            Block blockDeepCopy = GsonJsonizer.INSTANCE.getDeepCopy(block, Block.class);
            futures.add(service.submit(() -> threadPartialMining(blockDeepCopy, start, end)));
        }
    }

    private void terminate(Block block) {
        service.shutdown();
        try {
            if (!service.awaitTermination(2, TimeUnit.MINUTES)) {
                service.shutdownNow();
            }
            block.recalculateNextHashBySetting(nonce.get());
            System.out.println(GsonJsonizer.INSTANCE.toPrettyJson(block));
        } catch (InterruptedException e) {
            service.shutdownNow();
            Thread.currentThread().interrupt();
        }
        for (Future<?> future : futures) {
            if (!future.isDone()) {
                future.cancel(true);
            }
        }
    }


    @Override
    public void mineFor(Block block) {
        reset();
        createAndStartThreads(block);
        terminate(block);
    }

    private boolean nonceFound() {
        return nonce.get() >= 0;
    }

    private void threadPartialMining(Block block, int start, int end) {
        for (int i = start; i < end; i++) {
            if (Thread.currentThread().isInterrupted()
                    || nonceFound()) {
                break;
            }
            block.recalculateNextHashBySetting(i);
            if (Validator.INSTANCE.validate(block)) {
                nonce.set(i);
                break;
            }
        }
    }
}
