package org.rxjava.operator.map;


import com.google.common.collect.Lists;
import io.reactivex.Observable;
import org.rxjava.common.MyUtils;

import java.util.concurrent.TimeUnit;

public class MapObservable {
    public static void main(String[] args) {
        MapObservable client = new MapObservable();
//        client.test2();
        client.test3();
    }

    /**
     * flatMap不保证有序，有序要使用concatMap
     */
    public void test1() {
        Observable.just(1, 2)
                .flatMap(integer -> Observable.just(1, 2))
                .doOnNext(System.out::println)
                .subscribe();
    }

    public void test2() {
        Observable.just(1, 2)
                .flatMapIterable(integer -> Lists.newArrayList(integer, integer * 2))
                .doOnNext(System.out::println)
                .subscribe();
    }

    public void test3() {
        Observable.just(1, 2)
                .switchMap(integer -> Observable.interval(1, TimeUnit.SECONDS).take(2))
                .doOnNext(System.out::println)
                .subscribe();

        MyUtils.sleep(10000);
    }
}
