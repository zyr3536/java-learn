package rxjava.operator;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import rxjava.common.MyUtils;

/**
 * 切换线程的操作符
 */
@Slf4j
public class SchedulerOperator {

    public static void main(String[] args) {
        SchedulerOperator instance = new SchedulerOperator();
        instance.observeOn();
        MyUtils.sleep(100);
    }

    public void observeOn() {
        Single.just("")
                .doOnSuccess(s -> log.info(s))
                .observeOn(Schedulers.newThread())
                .doOnSuccess(s -> log.info(s))
                .subscribeOn(Schedulers.newThread())
                .doOnSuccess(s -> log.info(s))
                .flatMap(s ->
                        Single.just(s)
//                                .subscribeOn(Schedulers.newThread())
                                .observeOn(Schedulers.newThread())
                                .doOnSuccess(ss -> log.info(ss))
                )
                .doOnSuccess(s -> log.info(s))
                .subscribe();
    }
}
