package cn.changemax.ddd.infrastructure.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author WangJi
 * @Description 异步任务线程池配置类
 * @Date 2020/12/1 15:34
 */
@Slf4j
@Component
public final class ThreadPoolUtil {

    /**
     * CPU核心数
     */
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    // We want at least 2 threads and at most 4 threads in the core pool,
    // preferring to have 1 less than the CPU count to avoid saturating
    // the CPU with background work
    /**
     * 核心线程数，会一直存活，即使没有任务，线程池也会维护线程的最少数量
     * 密集型CPU：N+1
     * 2~4
     */
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));

    /**
     * 线程池维护线程的最大数量
     * 3 io密集型1
     */
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;

    /**
     * 线程池维护线程所允许的空闲时间 s
     */
    private static final int KEEP_ALIVE_SECONDS = 30;

    /**
     *线程池维护线程所允许的空闲时间单位
     */
    private static final TimeUnit UNIT = TimeUnit.SECONDS;

    /**
     * 队列初始化容量
     */
    private static final int QUEUE_CAPACITY = 128;

    /**
     * 线程名称头
     */
    private static final String THREAD_NAME_PREFIX = "ChangeMax-Async-Thread--";

    /**
     * 等待终止时间 s
     */
    private static final int AWAIT_TERMINATION_SECONDS = 60;

    /**
     * 线程工厂，为线程池提供创建新线程的功能
     */
    private static final ThreadFactory THREAD_FACTORY = new ThreadFactory() {
        /**
         * 线程计数
         */
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            log.info("create new thread...");
            return new Thread(r, "ChangeMax Thread #" + mCount.getAndIncrement());
        }
    };

    /**
     * 线程池中的任务队列，通过线程池的execute方法提交的Runnable对象会存储在这个参数中
     */
    private static final BlockingQueue<Runnable> TASK_QUEUE = new LinkedBlockingQueue<>(QUEUE_CAPACITY);

    /**
     * 线程池满 回退主线程策略
     */
    private static final RejectedExecutionHandler DEFAULT_HANDLER = new ThreadPoolExecutor.CallerRunsPolicy();

    /**
     * An {@link Executor} that can be used to execute tasks in parallel.
     */
    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR;

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_SECONDS, UNIT,
                TASK_QUEUE, THREAD_FACTORY, DEFAULT_HANDLER);

        //当设置allowCoreThreadTimeOut(true)时，线程池中"CORE_POOL_SIZE"线程空闲时间达到keepAliveTime也将关闭
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        THREAD_POOL_EXECUTOR = threadPoolExecutor;
        log.info("线程池实例化成功//   核心线程数:{}  维护线程最大数量:{}  维护线程所允许的空闲时间:{}{}", CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_SECONDS, UNIT);
    }

    public static ThreadPoolExecutor getPool() {
        return THREAD_POOL_EXECUTOR;
    }

}
