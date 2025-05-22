package com.lykourgoss.blockchainapi.core.helpers.threading;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceHelper {
    /**
     * Cancels all given futures with an interrupt request.
     *
     * @param futures List of futures to cancel
     */
    public static void cancelFutures(List<Future<?>> futures) {
        for (Future<?> future : futures) {
            if (future != null && !future.isDone()) {
                future.cancel(true);
            }
        }
    }

    /**
     * Gracefully shuts down an executor service.
     * Attempts to wait for termination and forcefully shuts down if timeout is exceeded.
     *
     * @param executor The executor to shut down
     * @param timeout  Timeout duration
     * @param unit     Time unit for the timeout
     */
    public static void shutdownExecutor(ExecutorService executor, long timeout, TimeUnit unit) {
        executor.shutdown(); // Stop accepting new tasks
        try {
            if (!executor.awaitTermination(timeout, unit)) {
                System.out.println("Forcing shutdown of executor...");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.out.println("Shutdown interrupted. Forcing shutdown...");
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Cancels futures and shuts down the executor service.
     *
     * @param futures  List of futures to cancel
     * @param executor The executor service to shut down
     * @param timeout  Timeout duration
     * @param unit     Time unit for the timeout
     */
    public static void cancelAndShutdown(List<Future<?>> futures,
                                         ExecutorService executor,
                                         long timeout,
                                         TimeUnit unit) {
        cancelFutures(futures);
        shutdownExecutor(executor, timeout, unit);
    }
}
