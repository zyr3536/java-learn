package org.resilience4j.circuitbreaker;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import lombok.val;

import java.time.Duration;
import java.util.NoSuchElementException;

public class Client {

    public static void main(String[] args) {
        val client = new Client();
        client.test1();
    }

    public CircuitBreakerRegistry registry() {
        CircuitBreakerRegistry registry = CircuitBreakerRegistry.ofDefaults();
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                // 打开熔断的阈值
                .failureRateThreshold(50)
                // ring buffer for half open
                .ringBufferSizeInHalfOpenState(4)
                // ring buffer for close state
                .ringBufferSizeInClosedState(4)
                // 从open到half-open需要的时间
                .waitDurationInOpenState(Duration.ofSeconds(1))
                //
                .automaticTransitionFromOpenToHalfOpenEnabled(false)
                // 需要忽略的异常
                .ignoreExceptions(NoSuchElementException.class)
                // 是否把某异常看成失败
                .recordFailure(throwable -> true)
                .build();

        registry.circuitBreaker("swh", config);

        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(70)
                .build();
        registry.addConfiguration("globalConfig", circuitBreakerConfig);
        return registry;
    }

    public void test1() {
        CircuitBreaker circuitBreaker = registry().circuitBreaker("swh", "globalConfig");
        note(circuitBreaker);
        CheckedFunction0<String> decorateCheckedSupplier = CircuitBreaker.decorateCheckedSupplier(circuitBreaker, this::service);

        val result = Try.of(decorateCheckedSupplier).map(v -> v + " world")
                .get();
        System.out.println(result);
    }

    public void note(CircuitBreaker circuitBreaker) {
        circuitBreaker.getEventPublisher()
                .onSuccess(e -> System.out.println("成功\r\n" + e));
    }


    public String service() {
        return "hello";
    }
}
