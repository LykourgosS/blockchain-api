package com.lykourgoss.blockchainapi.benchmarking;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.concurrent.TimeUnit;

@Getter
public class BenchmarkRunnerSettings {
    private final TimeUnit warmupTimeUnit;
    private final TimeUnit measurementTimeUnit;
    private final int forks;
    private final int warmupForks;
    private final int warmupIterations;
    private final int warmupTime;
    private final int measurementIterations;
    private final int measurementTime;

    public BenchmarkRunnerSettings(
            int forks,
            int warmupForks,
            int warmupIterations,
            int warmupTime,
            TimeUnit warmupTimeUnit,
            int measurementIterations,
            int measurementTime,
            TimeUnit measurementTimeUnit) {
        this.forks = forks;
        this.warmupForks = warmupForks;
        this.warmupIterations = warmupIterations;
        this.warmupTime = warmupTime;
        this.warmupTimeUnit = warmupTimeUnit;
        this.measurementIterations = measurementIterations;
        this.measurementTime = measurementTime;
        this.measurementTimeUnit = measurementTimeUnit;
    }

    public void calculateBenchmarkLastingTimeInMinutes() {
        long warmupTimeMinutes = TimeUnit.MINUTES.convert(warmupTime, warmupTimeUnit);
        long measurementTimeMinutes = TimeUnit.MINUTES.convert(measurementTime, measurementTimeUnit);
        long timePerFork = warmupTimeMinutes * warmupIterations + measurementTimeMinutes * measurementIterations;
        long totalForks = (forks == 0 ? 1 : forks) + warmupForks;
        long totaTime = totalForks * timePerFork;
        DateTimeFormatter systemFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String systemFormattedTime = LocalDateTime.now().format(systemFormatter);
        System.out.println("[" + systemFormattedTime + "] Lasting time of benchmark: " + totaTime + " minutes per method to benchmark (and per parameter if there are any!).");
    }
}
