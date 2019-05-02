package rxjava.operator;

import com.google.common.collect.Lists;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import rxjava.common.MyUtils;

/**
 * Rxjava的一些创建observable操作
 */
@Slf4j
@SuppressWarnings("all")
public class CreateOperator {

    public static void main(String[] args) {
        val ex = new CreateOperator();
//        ex.create();
//        ex.just();
        ex.from();
    }

    /**
     * create是最普通也是最灵活的创建操作
     */
    public void create() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                try {
                    // 建议在传递给create方法数据的时候,检查Observer的isDisposed状态,以便没有观察者的时候可以停止发射数据
                    if (!observableEmitter.isDisposed()) {
                        // 不能发射NULL
                        // observableEmitter.onNext(null);
                        for (int i = 1; i < 10; ++i) {
                            MyUtils.sleep(1000);
                            throw new Exception("hh");
                            // observableEmitter.onNext(i);
                        }
                        // 一个形式正确的有限Observable必须尝试调用观察者的onCompleted正好一次或者它的onError正好一次，
                        // 而且此后不能再调用观察者的任何其它方法
                        observableEmitter.onComplete();
                    }
                } catch (Exception e) {
                    // 出现异常,发送错误
                    Observable.error(e);
                }
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                log.info("订阅啦");

                new Thread() {
                    @Override
                    public void run() {
                        MyUtils.sleep(4000);
                        log.info("睡好了");
                        // dispose之后就不会发任何数据,包括complete和error
                        disposable.dispose();
                    }
                }.start();
            }

            @Override
            public void onNext(Integer integer) {
                log.info("收到数据:{}", integer);
            }

            @Override
            public void onError(Throwable throwable) {
                log.error("错误!", throwable);
            }

            @Override
            public void onComplete() {
                log.info("完成!");
            }
        });
    }

    public void just() {
        Observable.just(1, 2, 3)
                .doOnNext(i -> log.info(i.toString()))
                .subscribe();
        // 数组会被当作一个元素发出去
        Observable.just(Lists.newArrayList(1, 2, 3))
                .doOnNext(i -> log.info(i.toString()))
                .subscribe();
    }

    public void from() {
        // 数组还是作为一个整体发出
        Observable.fromArray(new int[]{1, 2, 3})
                .doOnNext(ints -> log.info("{}", ints))
                .subscribe();

        // 数组元素单独发送
        Observable.fromIterable(Lists.newArrayList(1, 2, 3))
                .doOnNext(integer -> log.info("{}", integer))
                .doOnComplete(() -> log.info("complete!"))
                .subscribe();


        // 具有延迟加载的功能
        Observable.fromCallable(() -> {
            log.info("延迟发送");
            return 1;
        })
                .doOnNext(integer -> log.info("{}", integer))
                .doOnSubscribe(disposable -> {
                    log.info("sleep for a while");
                    MyUtils.sleep(2000);
                    log.info("wake up");
                })
                .subscribe();
    }

    public void fromFuture() {
    }
}
