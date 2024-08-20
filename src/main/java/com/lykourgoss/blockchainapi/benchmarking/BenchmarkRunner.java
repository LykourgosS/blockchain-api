package com.lykourgoss.blockchainapi.benchmarking;

import org.aspectj.weaver.ClassAnnotationValue;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.File;

public class BenchmarkRunner {

    private final static Integer MEASUREMENT_ITERATIONS = 3;
    private final static Integer WARMUP_ITERATIONS = 3;

    private final Class<?> aClass;

    public BenchmarkRunner(Class<?> aClass) {
        this.aClass = aClass;
    }

    public void run() throws Exception {
        new Runner(getOptions()).run();
    }

    private String getResultFilePath(){
        String relativePath = "benchmark-results/" + System.currentTimeMillis() + ".json";
        File resultFile = new File(relativePath);
        File resultDir = resultFile.getParentFile();
        if (resultDir != null && !resultDir.exists()) {
            resultDir.mkdirs();
        }
        return resultFile.getAbsolutePath();
    }

    private Options getOptions(){
        return new OptionsBuilder()
                .include(aClass.getSimpleName())
                .forks(1)
                .warmupIterations(WARMUP_ITERATIONS)
                .measurementIterations(MEASUREMENT_ITERATIONS)
                .forks(1)
                .threads(1)
//                .shouldDoGC(true)
                .shouldFailOnError(true)
                .resultFormat(ResultFormatType.JSON)
                .result(getResultFilePath())
//                .jvmArgs("-server")
                .build();
    }
}
