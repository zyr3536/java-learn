package org.rxjava.operator.exception;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;

/**
 * @author: zyr
 * @date: 2019/5/15
 * @description: retry
 */
public class RetryOperator {
    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                System.out.println("订阅开始");
                emitter.onNext(1);
                emitter.onError(new Exception("1123123"));
            }
        })
                .retry(2)
                .doOnSubscribe(disposable -> System.out.println("subscribe"))
                .doFinally(() -> System.out.println("finally"))
                .doAfterTerminate(() -> System.out.println("after terminate"))
                .subscribe(integer -> System.out.println(integer),
                        throwable -> System.out.println("error:" + throwable.getMessage()));
    }
}
