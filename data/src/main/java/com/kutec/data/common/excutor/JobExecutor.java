package com.kutec.data.common.excutor;

import com.kutec.domain.common.excutor.ThreadExecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/3.
 */
@Singleton
public class JobExecutor implements ThreadExecutor {

    private static final int INITIAL_POOL_SIZE = 3;
    private static final int MAX_POOL_SIZE = 5;

    private static final int KEEP_ALIVE_TIME = 10;
    private static final TimeUnit KEEP_ALIVE_TIME_UNITE = TimeUnit.SECONDS;

    private final BlockingQueue<Runnable> workQueue;
    private final ThreadPoolExecutor threadPoolExecutor;
    private final ThreadFactory threadFactory;

    @Inject
    public JobExecutor() {
        this.workQueue = new LinkedBlockingQueue<>();
        this.threadFactory = new JobThreadFactory();
        this.threadPoolExecutor = new ThreadPoolExecutor(INITIAL_POOL_SIZE, MAX_POOL_SIZE,
                KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNITE, workQueue, threadFactory);
    }

    @Override
    public void execute(Runnable runnable) {
        if( runnable == null ) {
            throw new IllegalArgumentException("Runnable to execute cannot be null");
        }
        threadPoolExecutor.execute(runnable);
    }

    private static class JobThreadFactory implements ThreadFactory {
        private final String THREAD_NAME_PREFIX = "android_";
        private int count = 0;

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, THREAD_NAME_PREFIX + count++);
        }
    }
}
