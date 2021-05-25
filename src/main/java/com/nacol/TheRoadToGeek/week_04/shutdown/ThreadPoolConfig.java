package com.nacol.TheRoadToGeek.week_04.shutdown;

import com.nacol.TheRoadToGeek.week_03.netty_gateway.config.NamedThreadFactory;
import org.apache.commons.collections4.queue.SynchronizedQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Queue;
import java.util.concurrent.*;

@Configuration
public class ThreadPoolConfig {

    /**
     * @Description 线程池状态源码
     */
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    public static void main(String[] args) {
        System.out.println(RUNNING);
        System.out.println(SHUTDOWN);
        System.out.println(STOP);
        System.out.println(TIDYING);
        System.out.println(TERMINATED);
    }

    public static final int CORE_NUM = Runtime.getRuntime().availableProcessors() + 1;

    @Bean("taskPool")
    public Executor TaskPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("task-pool");
        executor.setCorePoolSize(CORE_NUM);
        executor.setAllowCoreThreadTimeOut(true);
        executor.setKeepAliveSeconds(30000);
        executor.setQueueCapacity(2000);
        executor.setMaxPoolSize(CORE_NUM * 2 - 1);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Bean("customPool")
    public ThreadPoolExecutor customPool() {
        //STEP 选择一种队列
        //固定大小阻塞队列， FIFO 排序
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(10);
        //不固定大小的队列，初始化一个大小，但是大小由 Integer.MAX_VALUE 决定
        // 当满载后，任务则 block ，当老任务被消费后，新任务才可写入, 顺序 FIFO
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue<>(10);
        //类似于 linkedBlockingQueue, 但是顺序由对象的自然顺序，或者 Comparator() 决定
        PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue<>(10);
        //特殊的存在，必须是放取交替完成
        Queue queue = new DelayQueue();
        SynchronizedQueue synchronizedQueue = SynchronizedQueue.synchronizedQueue(queue);

        //STEP 线程工厂
        ThreadFactory threadFactory = new NamedThreadFactory("nacol-gaga-thread-facotry", false);

        //STEP 拒绝策略
        //1. ThreadPoolExecutor.AbortPolicy: 默认策略，丢弃任务并抛出 RejectedExecutionException 异常。可捕获可用额外的方式来处理，并非实质地把任务丢了。
        //2. ThreadPoolExecutor.DiscardPolicy：丢弃任务，但是不抛出异常；
        //3. ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新提交被拒绝的任务 ；
        //4. ThreadPoolExecutor.CallerRunsPolicy：调用线程（提交任务的线程）处理该任务；（咱们小弟太忙了，老大你来帮忙吧）可做到不丢任务，并且缓冲线线程的压力。

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                CORE_NUM,
                CORE_NUM * 2,
                30000,
                TimeUnit.MILLISECONDS,
                priorityBlockingQueue,
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        return poolExecutor;
    }

    /**
     * 固定大小线程池
     */
    @Bean("fixedPool")
    public ExecutorService fixedPool() {
        //执行是无序的
        //可用于削峰，但是要注意长时间持续高峰的情况
        return Executors.newFixedThreadPool(10);
    }

    /**
     * 缓存线程池
     */
    @Bean("cachedPool")
    public ExecutorService cachedPool() {
        //可用于 Netty 来了大量短时请求
        return Executors.newCachedThreadPool();
    }

    /**
     * 调度线程池
     */
    @Bean("schedulePool")
    public ScheduledExecutorService schedulePool() {
        return Executors.newScheduledThreadPool(100);
    }



}
