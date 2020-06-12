package com.base.library.network;

import androidx.annotation.Nullable;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author reber
 */
public class PoolExecutorFactory {

    private void providePool() {
        AppPoolExecutor poolExecutor = new AppPoolExecutor(
                10,
                200,
                2,
                TimeUnit.HOURS,
                new LinkedBlockingQueue<>(10),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(@Nullable Runnable r) {
                        return null;
                    }
                },
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

                    }
                }
        );
    }
}
