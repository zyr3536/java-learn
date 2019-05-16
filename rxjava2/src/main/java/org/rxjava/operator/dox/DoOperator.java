package org.rxjava.operator.dox;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import org.rxjava.common.MyUtils;

/**
 * @author: zyr
 * @date: 2019/5/15
 * @description: todo
 */
public class DoOperator {
    public static void main(String[] args) {
        Observable.just(1,2,3,4)
                .map(integer -> integer * integer)
                .observeOn(Schedulers.newThread())
                .doOnNext(integer -> System.out.println(integer))
                .subscribe();
        MyUtils.sleep(1000);
    }
}
