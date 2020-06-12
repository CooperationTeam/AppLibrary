package com.base.library.network;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AppPoolExecutor extends ThreadPoolExecutor {

    /**
     * @param corePoolSize    核心线程数量
     * @param maximumPoolSize 该线程池中最大线程数量
     * @param keepAliveTime   非核心线程空闲时等待下一个任务到来的时间
     * @param unit            keepAliveTime的时间单位
     * @param workQueue       任务队列
     * @param threadFactory   线程工厂
     * @param handler         如果线程无法执行的回调
     */
    public AppPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    public void shutdown() {
        super.shutdown();
    }

    @Override
    @NonNull
    public List<Runnable> shutdownNow() {
        return super.shutdownNow();
    }

    @Override
    public void execute(Runnable command) {
        super.execute(command);
    }

    @Override
    @NonNull
    public Future<?> submit(Runnable task) {
        return super.submit(task);
    }
}
