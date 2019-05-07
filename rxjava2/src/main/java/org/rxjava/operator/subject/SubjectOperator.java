package org.rxjava.operator.subject;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.subjects.*;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.rxjava.common.MyUtils;

import java.util.concurrent.TimeUnit;

@Slf4j
@SuppressWarnings("all")
public class SubjectOperator {

    public static void main(String[] args) {
        val client = new SubjectOperator();
//        client.replayEx();
//        client.behaviorEx();
//        client.publishEx();
//        client.asyncEx();
//        client.completeEx();
        client.unicastEx();
        MyUtils.sleep(10000);
    }

    /**
     * ReplaySubject会重放所有数据
     */
    public void replayEx() {
        val subject = ReplaySubject.interval(1, TimeUnit.SECONDS);
        // first
        subject.subscribe(i -> log.info(i.toString()));
        MyUtils.sleep(2000);
        // second
        subject.subscribe(i -> log.info(i.toString()));
    }

    /**
     * behavior仅仅缓存订阅前的最后一个数据（包括complete和error）
     */
    public void behaviorEx() {
        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                System.out.println(o);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        };
        // observer will receive all 4 events (including "default").
        BehaviorSubject subject = BehaviorSubject.createDefault("default");
        subject.subscribe(observer);
        subject.onNext("one");
        subject.onNext("two");
        subject.onNext("three");

        System.out.println("================");

        // observer will receive the "one", "two" and "three" events, but not "zero"
        subject = BehaviorSubject.create();
        subject.onNext("zero");
        subject.onNext("one");
        subject.subscribe(observer);
        subject.onNext("two");
        subject.onNext("three");

        System.out.println("================");


        // observer will receive only onComplete
        subject = BehaviorSubject.create();
        subject.onNext("zero");
        subject.onNext("one");
        subject.onComplete();
        subject.subscribe(observer);

        System.out.println("================");


        // observer will receive only onError
        subject = BehaviorSubject.create();
        subject.onNext("zero");
        subject.onNext("one");
        subject.onError(new RuntimeException("error"));
        subject.subscribe(observer);
    }

    /**
     * PublishSubject仅会向Observer释放在订阅之后Observable释放的数据
     */
    public void publishEx() {
        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                System.out.println(o);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        };

        PublishSubject<Object> subject = PublishSubject.create();
        // observer1 will receive all onNext and onComplete events
        subject.subscribe(observer);
        subject.onNext("one");
        subject.onNext("two");

        System.out.println("==============");

        // observer2 will only receive "three" and onComplete
        subject = PublishSubject.create();
        subject.subscribe(observer);
        subject.onNext("three");
        subject.onComplete();
    }

    /**
     * 只能收到complete前的最后一个数据
     */
    public void asyncEx() {
        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                System.out.println(o);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        };
        // 观察者不会接收到任何数据，因为subject没有结束（onCompleted）
        AsyncSubject<Object> subject = AsyncSubject.create();
        subject.subscribe(observer);
        subject.onNext("one");
        subject.onNext("two");
        subject.onNext("three");

        System.out.println("==============");

        // 观察者只能接收到最后一件事件，即three事件
        subject = AsyncSubject.create();
        subject.subscribe(observer);
        subject.onNext("one");
        subject.onNext("two");
        subject.onNext("three");
        subject.onComplete();
    }

    /**
     * 只能收到complete,error数据
     */
    public void completeEx() {
        val subject = CompletableSubject.create();
        subject.onError(new RuntimeException("aa"));
        subject.onComplete();
        subject.subscribe(new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("complete");
            }
        }, throwable -> {
            System.out.println("error");
        });
    }

    /**
     * 仅支持一个订阅者
     */
    public void unicastEx() {
        val subject = UnicastSubject.create();
        subject.onNext("1");
        subject.onNext("2");
        subject.subscribe(o -> log.info(o.toString()));
        // 运行到此抛出异常
        subject.subscribe(o -> log.info(o.toString()));
        subject.onComplete();
    }

    /**
     * 使用普通的Subject，必须要注意不要在多线程情况下调用onNext 方法，
     * 这样是违反了Observable 协议并且会导致执行结果返回带有有歧义的值,
     * 所以需要使用SerializedSubscriber,内部使用synchronized加锁,线程安全
     */
    public void serializeEx() {
        val subject = PublishSubject.create().toSerialized();
        subject.onNext("zero");
        subject.onNext("one");
        subject.onError(new RuntimeException("error"));
        subject.subscribe(o -> log.info(o.toString()));
    }
}
