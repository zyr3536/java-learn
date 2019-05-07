package org.rxjava.backpressure;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.rxjava.common.MyUtils;

/**
 * 背压
 */
@Slf4j
public class FlowableEx {
    public static void main(String[] args) {
        val client = new FlowableEx();
//        client.flowable1();
        client.flowable2();
    }

    public void flowable1() {
        Flowable.create(emitter -> {
            log.info("request:{}", emitter.requested());
            emitter.onNext(1);
            log.info("request:{}", emitter.requested());
            emitter.onNext(2);
            log.info("request:{}", emitter.requested());
            emitter.onNext(3);
            log.info("request:{}", emitter.requested());
        }, BackpressureStrategy.ERROR)
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        log.info("subscribe!");
                        s.request(3);
                    }

                    @Override
                    public void onNext(Object o) {
                        log.info("{}", o);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        log.error("error", throwable);
                    }

                    @Override
                    public void onComplete() {
                        log.info("complete");
                    }
                });
    }


    public void flowable2() {
        Flowable.create(emitter -> {
            int i = 0;
            while (true) {
                if (emitter.requested() > 0) {
                    emitter.onNext(i++);
                }
            }
        }, BackpressureStrategy.BUFFER)
                .subscribe(new FlowableSubscriber<Object>() {
                    Subscription s = null;
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(1);
                        this.s = s;
                    }

                    @Override
                    public void onNext(Object o) {
                        log.info("{}", o);
                        MyUtils.sleep(1000);
                        // 每次发完一个数据再通知上游发数据
                        s.request(1);
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
