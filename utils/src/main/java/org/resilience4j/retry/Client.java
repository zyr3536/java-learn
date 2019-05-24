package org.resilience4j.retry;

import io.github.resilience4j.retry.IntervalFunction;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.val;

import java.text.MessageFormat;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * @author: zyr
 * @date: 2019/5/24
 * @description: 测试 retry功能
 */
public class Client {
    private AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        val client = new Client();

        client.test1();

    }

    public String service() {
        int tmp = count.get();
        System.out.println(MessageFormat.format("第{0}次", tmp));
        count.incrementAndGet();
        switch (tmp) {
            case 0:
                throw new RuntimeException();
            case 1:
                throw new NoSuchElementException();
            case 2:
                return "success";
            default:
                return "default";
        }
    }

    public RetryRegistry register() {
        RetryConfig config = RetryConfig.custom()
                // 最多重试次数
                .maxAttempts(3)
                // 失败之后下一次重试需要等待的时间(固定)
                //                .waitDuration(Duration.ofSeconds(1))
                // 可以修改重试需要的时间,比如说采用退避指数算法
                .intervalFunction(IntervalFunction.of(1000, aLong -> aLong * 2))
                // 根据返回结果来确定是否需要重试
                .retryOnResult(Objects::isNull)
                // 根据异常类型来决定是否需要返回
                .retryExceptions(RuntimeException.class)
                // 需要忽略的异常
                .ignoreExceptions(NullPointerException.class)
                .build();
        RetryRegistry registry = RetryRegistry.ofDefaults();
        registry.retry("swh", config);
        return registry;
    }

    public void test1() {

        Supplier<String> service = this::service;

        Supplier<String> supplier = Retry.decorateSupplier(register().retry("swh"), service);

        String s = supplier.get();
        System.out.println(s);

    }
}
