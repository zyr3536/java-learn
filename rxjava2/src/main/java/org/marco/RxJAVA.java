package org.marco;

import com.google.common.base.Stopwatch;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.concurrent.TimeUnit;

/**
 * @author: zyr
 * @date: 2019/4/28
 * @description: todo
 */
@SuppressWarnings("all")
@Slf4j
public class RxJAVA {
    public static void main(String[] args) throws Exception {
        val rxjava = new RxJAVA();
        rxjava.test();
        //        rxjava.process1();
        //        rxjava.process2();
        //        rxjava.process3();
        //        rxjava.process4();
        Thread.sleep(100000);
    }

    private void test() throws Exception {
        Observable
                .just(1, 2, 3, 4, 5, 6)

                .subscribe(integer -> log.info("\t{}", integer));
    }

    private void process1() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        val single = Single.fromCallable(Context::new)
                .map(this::step1)
                .map(this::step2)
                .observeOn(Schedulers.newThread())
                .map(this::step3)
                .map(this::step4)
                .subscribe((context, throwable) -> System.out
                        .println("process1 consume:" + stopwatch.elapsed(TimeUnit.MILLISECONDS)));
    }

    private void process2() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        val single1 = Single.fromCallable(Context::new)
                .map(this::step1);

        val single2 = single1.map(this::step2);

        val single3 = single1.observeOn(Schedulers.newThread()).map(this::step3);

        // merge
        Single.zip(single2, single3, (context, context2) -> {
            return context;
        })
                .map(this::step4)
                .subscribe((context, throwable) -> System.out
                        .println("process2 consume:" + stopwatch.elapsed(TimeUnit.MILLISECONDS)));

    }

    private void process3() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        val single1 = Single.fromCallable(Context::new)
                .map(this::step1).cache();

        val single2 = single1.observeOn(Schedulers.newThread()).map(this::step2);

        val single3 = single1.observeOn(Schedulers.newThread()).map(this::step3);

        // merge
        Single.zip(single2, single3, (context, context2) -> {
            return context;
        })
                .map(this::step4)
                .subscribe((context, throwable) -> System.out
                        .println("process3 consume:" + stopwatch.elapsed(TimeUnit.MILLISECONDS)));

    }

    private void process4() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        val single1 = Single.fromCallable(Context::new)
                .map(context -> {
                    context.setCache(false);
                    context.setDone(false);
                    return context;
                })
                .map(this::step1).cache();

        val single2 = single1.observeOn(Schedulers.newThread()).map(this::step2);

        val single3 = single1.observeOn(Schedulers.newThread()).map(this::step3);

        // merge
        val single4 = Single.zip(single2, single3, (context, context2) -> {
            return context;
        })
                .map(this::step4)
                .flatMap(context -> {
                    Single<Context> singleCache = Single.just(context).map(this::cache);
                    Single<Context> sigleNet = Single.just(context).map(this::net);
                    return Single.concat(sigleNet, singleCache).takeUntil(new Predicate<Context>() {
                        @Override public boolean test(Context o) throws Exception {
                            return o.isDone();
                        }
                    }).firstElement().toSingle();
                })
                .subscribe((context, throwable) -> {
                    System.out.println("process4 consume:" + stopwatch.elapsed(TimeUnit.MILLISECONDS));
                    System.out.println("用没用缓存: " + (context.isCache() ? "用了" : "没用"));
                });

    }

    private Context step1(Context context) {
        // step 1...
        System.out.println("step 1 finish!");
        return context;
    }

    private Context step2(Context context) throws Exception {
        // step 2...
        Thread.sleep(3000);
        System.out.println("step 2 finish!");
        return context;
    }

    private Context step3(Context context) throws Exception {
        // step 3...
        Thread.sleep(2000);
        System.out.println("step 3 finish!");
        return context;
    }

    private Context step4(Context context) {
        // step 4...
        System.out.println("step 4 finish!");
        return context;
    }

    private Context cache(Context context) throws Exception {
        System.out.println("开始查询缓存");
        Thread.sleep(10);
        context.setCache(true);
        context.setDone(true);
        System.out.println("查询缓存结束");
        return context;
    }

    private Context net(Context context) throws Exception {
        System.out.println("开始IO");
        Thread.sleep(2000);
        context.setCache(false);
        context.setDone(false);
        System.out.println("写入缓存");
        System.out.println("Io结束");
        return context;
    }
}

@Getter
@Setter
@AllArgsConstructor
class Context {
    Context() {
        System.out.println("Context  initialize!");
    }

    private boolean cache;

    private boolean done;
}
