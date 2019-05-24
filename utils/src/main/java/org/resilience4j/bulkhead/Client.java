package org.resilience4j.bulkhead;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.github.resilience4j.bulkhead.ThreadPoolBulkheadConfig;
import io.github.resilience4j.bulkhead.ThreadPoolBulkheadRegistry;
import lombok.val;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zyr
 * @date: 2019/5/24
 * @description: 限制并发测试
 */
public class Client {

    private final AtomicInteger a = new AtomicInteger(0);

    public static void main(String[] args) {
        val client = new Client();
        client.test1();
    }

    /**
     * 信号量方式
     */
    public void test1() {
        BulkheadConfig config = BulkheadConfig.custom()
                // 最大并发数
                .maxConcurrentCalls(1)
                // 最大阻塞时间
                .maxWaitTime(0)
                .build();

        Bulkhead bulkhead = Bulkhead.of("swh", config);

        Callable<Integer> supplier = Bulkhead.decorateCallable(bulkhead, () -> {
            Thread.sleep(1000);
            return 0;
        });
        ExecutorService executorService =
                new ThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(4));
        execute(executorService, 100, supplier);
    }

    private void execute(ExecutorService executorService, int nums, Callable<Integer> supplier) {
        for (int i = 0; i < nums; ++i) {
            executorService.execute(() -> {
                try {
                    supplier.call();
                } catch (Exception e) {
                    System.out.println(e);
                }
                System.out.println(a.get());
                a.incrementAndGet();
            });
        }
    }

    /**
     * 线程池方式
     */
    public void test2() {
        ThreadPoolBulkheadConfig config = ThreadPoolBulkheadConfig.custom()
                .coreThreadPoolSize(1)
                .maxThreadPoolSize(2)
                .queueCapacity(0)
                .keepAliveTime(1000)
                .build();

        ThreadPoolBulkheadRegistry registry = ThreadPoolBulkheadRegistry.of(config);

        registry.bulkhead("swh", config);
    }
}
