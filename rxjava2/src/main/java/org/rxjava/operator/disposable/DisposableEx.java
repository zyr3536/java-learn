package org.rxjava.operator.disposable;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import lombok.val;
import org.rxjava.common.MyUtils;

import java.util.concurrent.TimeUnit;

/**
 * 取消订阅就会切断上游和下游的联系
 * 有一点需要注意的是，在发送数据的时候最好要判断一下是否已经disposal里，如果是就没必要再发数据了
 * ps:这里代码只是做了演示所以没按前面说的那么做
 */
@SuppressWarnings("all")
public class DisposableEx {
    public static void main(String[] args) {
        val client = new DisposableEx();
//        client.disposable1();
//        client.disposable2();
//        client.disposable3();
        client.disposable4();
        MyUtils.sleep(100000);
    }

    // 第一种方式
    public void disposable1() {
        Disposable disposable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                int i = 0;
                while (true) {
                    emitter.onNext(Integer.toString(i));
                    System.out.println("发射数据:" + i);
                    i = i + 1;
                    MyUtils.sleep(1000);
                }
            }
        })
                .subscribeOn(Schedulers.newThread())
                .subscribe(s -> System.out.println("收到数据:" + s));
        new Thread(new Runnable() {
            @Override
            public void run() {
                MyUtils.sleep(3000);
                disposable.dispose();
                // fixme 这句话貌似会中断订阅线程，不知道为什么，之后细细研究下
                System.out.println("取消订阅");
            }
        }).start();
    }

    //  第二种方式
    public void disposable2() {
        Observable.interval(1, TimeUnit.SECONDS)
                .doOnNext(System.out::println)
                .subscribe(new Observer<Long>() {
                    private Disposable myDisposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        myDisposable = d;
                    }

                    @Override
                    public void onNext(Long aLong) {
                        if (aLong >= 3) {
                            myDisposable.dispose();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 第三种，利用DisposableObserver
     */
    public void disposable3() {
        DisposableObserver<Long> disposableObserver =
                Observable.interval(1, TimeUnit.SECONDS)
                        .subscribeWith(new DisposableObserver<Long>() {
                            @Override
                            public void onNext(Long aLong) {
                                System.out.println(aLong);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
        MyUtils.sleep(5000);
        System.out.println("取消订阅");
        disposableObserver.dispose();
    }

    /**
     * 第四种，推荐使用，适合批量取消
     */
    public void disposable4() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        DisposableObserver<Long> disposableObserver =
                Observable.interval(1, TimeUnit.SECONDS)
                        .subscribeWith(new DisposableObserver<Long>() {
                            @Override
                            public void onNext(Long aLong) {
                                System.out.println(aLong);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
        // 把所有disposable都add进去就可以一次性取消
        compositeDisposable.add(disposableObserver);
        MyUtils.sleep(5000);
        System.out.println("批量取消订阅");
        compositeDisposable.clear();
    }
}
