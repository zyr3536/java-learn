package org.resilience4j.timelimiter;

import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.vavr.control.Try;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * @author: zyr
 * @date: 2019/5/24
 * @description: 时间控制
 */
public class Client {
    public static void main(String[] args) throws Exception {
        TimeLimiterConfig config = TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(1))
                .cancelRunningFuture(true)
                .build();

        TimeLimiter timeLimiter = TimeLimiter.of(config);

        Supplier<CompletableFuture> futureSupplier = () -> CompletableFuture.supplyAsync(() -> {
            long begin = System.currentTimeMillis();
            while (begin + 10000 > System.currentTimeMillis()) {
                // ...
            }
            return 1;
        });


        Callable<Integer> callable = TimeLimiter.decorateFutureSupplier(timeLimiter, futureSupplier);

        Try.of(callable :: call).onFailure(e -> System.out.println(e));



    }
}
