package org.rxjava.operator.blocking;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zyr
 * @date 2019/6/13
 * @description 真搞, 不是假搞
 */
@Slf4j
public class BlockingOperator {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ExecutorService executorService2 = Executors.newFixedThreadPool(5);
        log.info("start");
        Flowable.just(1, 2, 3, 4, 5)
                .parallel(5)
                .runOn(Schedulers.from(executorService))
                .doOnNext(integer -> log.info("{}", integer))
                .sequential()
                .doOnNext(integer -> log.info("{}", integer))
                //                .observeOn(Schedulers.from(executorService2))
                //                .doOnNext(integer -> log.info("{}", integer))
                .blockingForEach(integer -> log.info("{}", integer));
        log.info("finish");
    }
}
