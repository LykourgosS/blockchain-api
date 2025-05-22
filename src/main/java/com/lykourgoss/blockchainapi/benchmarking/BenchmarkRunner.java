package com.lykourgoss.blockchainapi.benchmarking;

import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import org.openjdk.jmh.runner.options.VerboseMode;

import java.io.File;

public class BenchmarkRunner {
    private final Class<?> aClass;
    private final BenchmarkRunnerSettings settings;

    public BenchmarkRunner(Class<?> aClass, BenchmarkRunnerSettings settings) {
        this.aClass = aClass;
        this.settings = settings;
    }

    public void run() throws Exception {
        new Runner(getOptions()).run();
    }

    private String getResultFilePath() {
        String relativePath = "benchmark-results/" + System.currentTimeMillis() + ".json";
        File resultFile = new File(relativePath);
        File resultDir = resultFile.getParentFile();
        if (resultDir != null && !resultDir.exists()) {
            resultDir.mkdirs();
        }
        return resultFile.getAbsolutePath();
    }

    private Options getOptions() {
        return new OptionsBuilder()
                .include(aClass.getSimpleName())
                .forks(settings.getForks())
                .warmupForks(settings.getWarmupForks())
                .warmupIterations(settings.getWarmupIterations())
                .warmupTime(new TimeValue(settings.getWarmupTime(), settings.getWarmupTimeUnit()))
                .measurementIterations(settings.getMeasurementIterations())
                .measurementTime(new TimeValue(settings.getMeasurementTime(), settings.getMeasurementTimeUnit()))
                .shouldFailOnError(true)
                .resultFormat(ResultFormatType.JSON)
                .result(getResultFilePath())
                .verbosity(VerboseMode.EXTRA)
                .build();
    }
}
