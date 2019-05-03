package rxjava.operator;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

/**
 * 切换线程的操作符
 */
@Slf4j
public class SchedulerOperator {

    public static void main(String[] args) {
        SchedulerOperator instance = new SchedulerOperator();
        instance.schedule2();
    }

    public void schedule() {
        Single.just("")
                .doOnSuccess(s -> log.info(s))
                .observeOn(Schedulers.newThread())
                .doOnSuccess(s -> log.info(s))
                .subscribeOn(Schedulers.newThread())
                .doOnSuccess(s -> log.info(s))
                .flatMap(s ->
                        Single.fromCallable(() -> {
                            log.info(s);
                            return s;
                        })
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(Schedulers.newThread())
                                .doOnSuccess(ss -> log.info(ss))
                )
                .doOnSuccess(s -> log.info(s))
                .subscribe();
    }

    public void schedule2() {
        Single.just("")
                .observeOn(Schedulers.newThread())
                .doOnSuccess(s -> log.info(s))
                .subscribeOn(Schedulers.newThread())
                .flatMap(s ->
                        Single.fromCallable(() -> {
                            log.info(s);
                            return s;
                        })
                                .subscribeOn(Schedulers.newThread())
                                .doOnSuccess(ss -> log.info(ss))
                )
                .doOnSuccess(s -> log.info(s))
                .subscribe();
    }

}
